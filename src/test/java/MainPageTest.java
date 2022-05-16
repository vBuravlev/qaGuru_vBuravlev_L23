import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPageTest {

    @Test
    @DisplayName("Слайдер \"Продукты и решения\"")
    @Story("Работа слайдера")
    @Tag("web")
    @Owner("buravlev")
    public void sliderProdAndSolution() {
        step("Открываем главную страницу", () -> {
            open("https://tionix.ru/");
        });

        step("Кликаем по слайдеру 'Виртуализация облака'", () -> {
            $x("//div[text()='Русскоязычная служба технической поддержки, работающая в режиме 24/7*365']").scrollTo();
            $x("//div[text()='Виртуализация облака ']").click();
        });

        step("Проверяем URL страницы при активации слайдера", () -> {
            webdriver().shouldHave(url("https://tionix.ru/#!/tab/276762841-2"));
        });

        step("Кликаем по кнопке 'Подробнее'", () -> {
            $x("//div[text()='TIONIX Cloud Platform']/../a[@href='/services/tionix-cloud-platform/']").click();
        });

        step("Проверяем URL страницы после перехода", () -> {
            webdriver().shouldHave(url("https://tionix.ru/services/tionix-cloud-platform/"));
        });
    }


    @Test
    @DisplayName("Frame Обратная связь")
    @Story("Контакты")
    @Tag("web")
    @Owner("buravlev")
    public void feedbackFrame() {
        step("Открываем главную страницу", () -> {
            open("https://tionix.ru/");
        });

        step("Кликаем по вкладке 'Контакты'", () -> {
            $x("//a[text()='Напишите нам']").scrollTo().click();
            String feedBack = $("div.t-popup__container>div>div").text();
            assertThat(feedBack.equals("Обратная связь "));
        });
    }

    @Test
    @DisplayName("Кнопка 'Другие новости'")
    @Story("Новости")
    @Tag("web")
    @Owner("buravlev")
    public void diffNews() {
        step("Открываем главную страницу", () -> {
            open("https://tionix.ru/");
        });

        step("Кликаем по кнопке 'Другие новости'", () -> {
            $x("//div[text()='Новости компании']").scrollTo();
            $x("//a[@href='/news']").click();
        });

        step("Проверяем URL страницы новостей", () -> {
            webdriver().shouldHave(url("https://tionix.ru/news"));
        });

    }


    @Test
    @DisplayName("Реферальные ссылки на партнеров")
    @Story("Партнеры")
    @Tag("web")
    @Owner("buravlev")
    public void partnersRef() {
        step("Открываем главную страницу", () -> {
            open("https://tionix.ru/");
        });

        step("Кликаем по логотипу Ростелеком", () -> {
            $x("//div[text()='Входим в группу компаний']").scrollTo();
            $x("//img[contains(@src,'Logo_Rostelecom.svg')]").click();
        });

        step("Проверяем URL страницы Ростелеком", () -> {
            switchTo().window(1);
            webdriver().shouldHave(currentFrameUrlContaining(".rt.ru"));
        });

    }

    @Test
    @DisplayName("Скачивания Руководства пользователя")
    @Story("Документация")
    @Tag("web")
    @Owner("buravlev")
    public void downloadUserManual() {

        step("Открываем главную страницу", () -> {
            open("https://tionix.ru/");
        });

        step("Открываем руководство пользователя в подвале сайта", () -> {
            $x("//div[text()='Документация']").scrollTo();
            $x("//a[text()='Руководство пользователя']").click();
        });

        step("Проверяем URL страницы Ростелеком", () -> {
            switchTo().window(1);
            webdriver().shouldHave(currentFrameUrlContaining("https://files.tionix.ru/"));
        });

    }


}
