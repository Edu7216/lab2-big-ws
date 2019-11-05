package translator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import translator.Application;
import translator.domain.TranslatedText;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TranslatorServiceTest {

  @Autowired
  TranslatorService translatorService;

  @Test
  public void translateTest() {
    TranslatedText translatedText = translatorService.translate("en", "es", "This is a test of translation service");
    assertEquals("Esto es una prueba de servicio de traducción", translatedText.getTranslation());
  }

  @Test
  public void translateTest1() {
    TranslatedText translatedText = translatorService.translate("id", "es", "Ini adalah bukti layanan terjemahan");
    assertEquals("Esta es una prueba de servicio de traducción", translatedText.getTranslation());
  }

  @Test
  public void translateTest2() {
    TranslatedText translatedText = translatorService.translate("pt", "es", "Este tradutor funciona mal");
    assertEquals("Este traductor funciona mal", translatedText.getTranslation());
  }
  
}
