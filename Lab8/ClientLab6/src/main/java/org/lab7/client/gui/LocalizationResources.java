package org.lab7.client.gui;

import java.util.HashMap;

public class LocalizationResources {
    private static final HashMap<String, HashMap<String, String>> messages = new HashMap<>();
    String language;
    public LocalizationResources(String language) {
        this.language = language;
        HashMap<String, String> ruMessages = new HashMap<>();
        HashMap<String, String> ukMessages = new HashMap<>();

        ruMessages.put("имя", "Имя пользователя: ");
        ruMessages.put("пароль", "Пароль: ");
        ruMessages.put("войти", "Войти");
        ruMessages.put("зарегистироваться", "Зарегистрироваться");
        ruMessages.put("далее", "Далее");
        ruMessages.put("пользователь", "Текущий пользователь: ");
        ruMessages.put("инфа","Подробная информация о коллекции");
        ruMessages.put("ввод", "Введите команду: ");
        ruMessages.put("отправить", "Отправить");
        ruMessages.put("назад", "Назад");
        ruMessages.put("ответ", "Ответ сервера: ");
        ruMessages.put("фильтр", "Отфильтровать");
        ruMessages.put("очистить", "Очистить");
        ruMessages.put("редактировать", "Редактировать?");
        ruMessages.put("введите", "Введите ");
        ruMessages.put("неправильно", "Неправильно введена комнада и/или значения!");
        ruMessages.put("войдите", "Войдите или зарегистрируйтесь!");
        ruMessages.put("поле", "Введите название поля, которое хотите заменить (enginePower/capacity):");
        ruMessages.put("значение", "Введите новое значение:");
        ruMessages.put("неверный логин", "Неверный логин или пароль!");
        ruMessages.put("введите всё", "Введите имя пользователя и пароль!");
        ruMessages.put("занято", "Пользователь с таким именем уже существует!");
        ruMessages.put("отфильтровать", "Введите значения для фильтрации: ");

        messages.put("russian", ruMessages);


        ukMessages.put("имя", "Ім'я користувача: ");
        ukMessages.put("пароль", "Пароль: ");
        ukMessages.put("войти", "Увійти");
        ukMessages.put("зарегистироваться", "Зареєструватися");
        ukMessages.put("далее", "Далі");
        ukMessages.put("пользователь", "Поточний користувач: ");
        ukMessages.put("инфа","Детальна інформація про колекцію");
        ukMessages.put("ввод", "Введіть команду: ");
        ukMessages.put("отправить", "Надіслати");
        ukMessages.put("назад", "Назад");
        ukMessages.put("ответ", "Відповідь сервера: ");
        ukMessages.put("фильтр", "Відфільтрувати");
        ukMessages.put("очистить", "Очистити");
        ukMessages.put("редактировать", "Редагувати?");
        ukMessages.put("введите", "Введіть ");
        ukMessages.put("неправильно", "Неправильно введена кімната та/або значення!");
        ukMessages.put("войдите", "Увійдіть або зареєструйтесь!");
        ukMessages.put("поле", "Введіть назву поля, яку хочете замінити (enginePower/capacity):");
        ukMessages.put("значение", "Введіть нове значення:");
        ukMessages.put("неверный логин", "Невірний логін або пароль!");
        ukMessages.put("введите всё", "Введіть ім'я користувача та пароль");
        ukMessages.put("занято", "Користувач з таким ім'ям вже існує!");
        ukMessages.put("отфильтровать", "Введіть значення для фільтрації:");

        messages.put("ukrainian", ukMessages);
    }

    public String getMessage(String message) {
        return messages.get(language).get(message);
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
