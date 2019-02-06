package ru.grigorev.epam.homework.JavaSE3.task2;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Разработать приложение, позволяющее по выбору пользователя использовать русский
 * или английский язык, для отображения информации. Приложение должно представлять собой
 * перечень вопросов под номерами (на английском или русском соответственно). Выбрав номер
 * вопроса пользователь может узнать ответ на него.
 *
 * @author Dmitriy Grigorev
 */
public class QuestionApp {
    @Getter
    private ResourceBundle bundleQuestions;
    @Getter
    private ResourceBundle bundleAnswers;
    @Getter
    private ResourceBundle bundleUI;

    public QuestionApp() {
        setBundles(new Locale("en"));
    }

    public void setRU() {
        setBundles(new Locale("ru"));
    }

    public void setEN() {
        setBundles(new Locale("en"));
    }

    private void setBundles(Locale locale) {
        bundleQuestions = ResourceBundle.getBundle("questions", locale);
        bundleAnswers = ResourceBundle.getBundle("answers", locale);
        bundleUI = ResourceBundle.getBundle("ui", locale);
    }

    /**
     * Gets and process input from console
     *
     * @throws IOException
     */
    public void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose language/Выберете язык: (en/ru)");

        String input = "";
        while (true) {
            input = br.readLine();
            if (input.equals("exit")) {
                System.out.println(bundleUI.getString("goodbye"));
                break;
            }
            if (checkLanguage(input)) continue;
            if (checkRightInput(input)) System.out.println(bundleAnswers.getString(input));
        }
    }

    /**
     * @param input
     * @return true if language has changed
     */
    private boolean checkLanguage(String input) {
        if (input.equalsIgnoreCase("ru")) {
            setRU();
            showLanguageConfirmation();
            showAllQuestions();
            return true;
        }
        if (input.equalsIgnoreCase("en")) {
            setEN();
            showLanguageConfirmation();
            showAllQuestions();
            return true;
        }
        return false;
    }

    private void showLanguageConfirmation() {
        System.out.println(bundleUI.getString("chosenLang"));
        System.out.println(bundleUI.getString("typeExit"));
        System.out.println(bundleUI.getString("typeQuestionNumber"));
    }

    private void showAllQuestions() {
        Enumeration<String> keys = bundleQuestions.getKeys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = bundleQuestions.getString(key);
            System.out.println(key + ": " + value);
        }
    }

    /**
     * @param input
     * @return true - if input is correct (it is a number and in range of available questions)
     */
    public boolean checkRightInput(String input) {
        try {
            int i = Integer.parseInt(input);
            int count = Integer.parseInt(bundleUI.getString("getQuestionCount"));
            if (i < 1 || i > count) {
                System.out.println(bundleUI.getString("wrongQuestionNumber"));
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println(bundleUI.getString("parseError"));
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        QuestionApp app = new QuestionApp();

        try {
            app.getInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
