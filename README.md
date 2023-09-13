# SELENIUM
____

## Ожидания (Wait VS sleep)
- __Thread.sleep(1000)__ - останавливает выполнение программы на 1 секунду
- __WebDriver Wait__ ожидает некоторого события

Типы ожиданий
- Implicit wait
- Explicit wait
- Fluent wait

### Implicit wait
Одна из разновидностей ожиданий в Selenium. При включении будет работать одинаково для всех элементов
Обычно настраивается один раз, но настройки можно менять.

- ожидает пока элемент не будет показан на странице
- ожидает указанный timeout
- если элемент не будет показан, он выбросит `org.openqa.selenium.NoSuchElementException: no such element`

> Пример:
>  ```java
> driver.manage().timeouts().implcitlyWait(30, TimeUnit.SECONDS);
> ```

### Explicit wait
Это индивидуальная настройка timeout для элементов страницы.
Возможности гибкой настройки:
1. Кастомизация настройки timeout
2. Гибкое ожидание для событий
   - присутствие элемента
   - видимость элемента
   - загружена ли страница
   - возвращение некоторого значения JavaSriptом
> Пример:
>
> ```java
> WebDriverWait wait = new WebDriverWait(driver, 30);
> wait.until(ExpectedConditions.elementToBeClickable(searchButton))
> ```

### Важно помнить
- Explicit wait не будет работать, если уже включен Implicit wait
- Не стоит одновременно использовать Implicit и Explicit wait
- Если необходимо использовать Implicit и Explicit wait вместе, то можно использовать:
>```java 
> driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS)
>```

### Пример custom-ожидания
```java
boolean isElementDisplayed(By by, int timeout) throws
InterruptedException {
    List<WebElement> elements = driver.findElements(by);
    for(int i = 0; (i < timeout) && (elements.size() == 0); i++) {
        Thread.sleep(1000);
        elements = driver.findElements(by);
    }
    return elements.size() > 0;
}
```
### Задание
> 1. Открыть сайт google.com
> 2. Подождать пока не будет виден заголовок страницы
> 3. Подождать пока кнопка поиска не будет кликабелной
> 4. Ввести значение в поле ввода
> 5. Нажать кнопку поиска
> 6. Ждем пока заголовок страницы не перестанет быть видимым





