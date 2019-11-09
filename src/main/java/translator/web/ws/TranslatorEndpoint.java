package translator.web.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import translator.domain.TranslatedText;
import translator.exception.TranslatorException;
import translator.service.TranslatorService;
import translator.web.ws.schema.GetTranslationRequest;
import translator.web.ws.schema.GetTranslationResponse;

@Endpoint
public class TranslatorEndpoint {

  private final TranslatorService translatorService;

  @Autowired
  public TranslatorEndpoint(TranslatorService translatorService) {
    this.translatorService = translatorService;
  }

  /*
   * Modified the main functionality, now it is possible to pass a file as text to traduce.
   * Idea took from: https://github.com/spring-projects/spring-ws-samples/tree/master/mtom
   */
  @PayloadRoot(namespace = "http://translator/web/ws/schema", localPart = "getTranslationRequest")
  @ResponsePayload
  public GetTranslationResponse translator(@RequestPayload GetTranslationRequest request) {
    GetTranslationResponse response = new GetTranslationResponse();
    try {
      String received = new String(request.getText());
      String parsed = received.substring(0, received.length()-1);
      TranslatedText translatedText = translatorService.translate(request.getLangFrom(), request.getLangTo(),
              parsed);
      response.setResultCode("ok");
      response.setTranslation(translatedText.getTranslation().getBytes());
    } catch (TranslatorException e) {
      response.setResultCode("error");
      response.setErrorMsg(e.getMessage());
    }
    return response;
  }

}
