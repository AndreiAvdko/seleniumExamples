# SELENIUM
____

## Необходимые действия для использования Selenium
1. Загрузить WebDriver.exe 
2. Положить файл драйвера в папку /test/resources

## Создание WebDriver
```java
// Cохраняем драйвер и путь к нему в системные переменные
// Необходимо для того, чтобы при создании инстанса драйвера стартовал браузер 
// под управлением нужного браузера
File file = new File("src/test/resources/chromedriver.exe");
System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
ChromeOptions chromeOptions = new ChromeOptions();
WebDriver driver = new ChromeDriver(chromeOptions);
// Изменение стратегии загрузки страницы
chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
```
1. Примеры создания локального веб-драйвера
```java
WebDriver driver = new ChromeDriver;
WevDriver driver = new InternetExplorerDriver();
```
2. Удалённый веб-драйвер
```java
// desiredCapabilities - конфигурации WebDriver-а
WebDriver driver = new RemoteWebDriver(remoteURL, desiredCapabilities);
```

## Локаторы
Типы локаторов в Selenium
```java
  By.id("lst-ib");
    By.name(q"");
  By.className("gsfi");
    By.tagName("input");
  By.cssSelector("input#lst-ib");
    By.xpath("//input[@aria-label='Search']");
  By.linkText("Gmail");
    By.likPartialText("mail");
```

Также локаторы можно искать с помощью аннотации @FindBy
```java
   // Вместо этого
WebElement clearButton = new driver.findElement(By.id("clear"));
   // это
@FindBy(id = "clear")
WebElement clearButton;

// Другой пример
@FindBy(xpath = "somexpath")
WebElement clearButton;
```
### Критерии хороших локаторов
- уникальность
- редко меняются
- быстродействие и производительность
- лёгкие для понимания

## Базовые действия в Selenium
### Переходы по URL, вперёд/назад 
```java
driver.navigate().to("https://www.google.com/")
// or
driver.get("https://www.googl.com/")

driver.navigate().to()
                 .refresh()
                 .back()
                 .forward()

// Закрытие WebDriver
    // Закрывает все окна
        driver.quit();
    // Закрывает текущее окно
        driver.close();
```
### Базовые действия с элементами
```java
    click()
    sendKeys("some string")
    sendKeys(Keys.RETURN)
    getText()
    getAttribute("value")
```
Пример использования действий в Selenium
```java
   WebElement searchField = driver.findElement(By.id("lst-ib"));
   searchField.sendKeys("selenium java");
   searchField.sendKeys(Keys.RETURN);
```

### Продвинутые действия 
Пример использования "продвинутых действий" в Selenium
Для использования доп. действий необходим `class Actions: OpenQA.Selenium.Interactions.Actions`

> Примерный перечень доп. действий
> - click()
> - clickAndHold()
> - release()
> - sendKeys()
> - moveToElement()
> - doubleClick()
> - dragAndDrop()
> - contextClick()

Пример 1. Подвести курсор к элементу и выполнить двойной клик
```java
Actions builder = new Actions(driver);
    builder.moveToElement(element).doubleClick().build().perform();
```
Пример 2. Подвести курсор к элементу (чтобы увидеть tooltip)
```java
Actions builder = new Actions(driver);
    builder.moveToElement(goolgeLable).build().perform();    
```
Пример 3. Симуляция CTRL+V action
```java
public void pasteTextToElementFromClipboard(WebElement element, String text) {
    // Копирование текста в буфер
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = tolkit.getSystemClipboard();
    StringSelection strSelection = new StringSelection(text);
    clipboard.setContents(strSelecion, null);
    // Вставка элемента в поле 
    elements.sendKeys(Keys.CONTROL, "v");
}
```

### Действия с JavaScript

Пример 1. Клик на элементе
```java
    WebElement element = driver.findElement(By.id("test"));
    JavascriptExecutr executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();").element);
```
Пример 2. Вставка текста с JavaScript
```java
   WebElement field = driver.findElement(By.id("test"));
   JavascriptExecutor executor = (JavascriptExecutor) driver;
   executor.executeScript("arguments[0].value = '" + text + "'", field);
```

Пример 3. Действия с frame, alert, window
```java
    // 1. Frame
   driver.switchTo().frame("frameName");

    // 2. Alert
    Alert alert = driver.switchTo().alert();
    
    // 3. Window
    for (String handle : driver.getWindowHandles()) {
        driver switchTo().window(handle);
           }
```

# Паттерны проектирования тестов
## PageObject
Какие проблемы решает:
- обеспечивает переиспользование кода
- ограничивает список возможных действий на странице
- определяет элементы и действия над ними
- облегчает дальнейшую поддержку тестового фреймворка и читабельность тестов

>__Проблема реализации:__
В данном паттерне для объявления полей страницы используется аннотация @FindBy, тем
самым при инициализации объекта страницы элементы могут быть не найдены(NoSuchElementException).
Для решения данной проблемы необходимо использовать __ленивую инициализацию__ с помощью __PageFactory__.



## Steps
Позволяет описать шаги с помощью языка близкого к шагам в тест-кейсах
При реализации появляется дополнительный слой business logic, проект начинает иметь примерно следующую структуру:
- pages
   * BasePage
   * Search Page
   *  SearchResultsPage
- steps
   * SearchResultsSteps
   * SearchSteps

__Плюсы подхода:__
- ограничивает определённые действия на странице
- по внешнему виду автотест становится похож на обычный тест-кейс
- связь между страницами скрыта внутри шагов

## Chain Of Invocation

## __Примерная структура проекта с использованием вышеперечисленных паттернов__
- pages
  * BasePage
  ```java
  SearchSteps steps;
  
  @BeforeClass
  public void setUp() {
        // some code here
        steps = new SearchSteps();
  }
  ```
  * Search Page
  ```java

  ``` 
  * SearchResultsPage
  ```java

  ```
- steps
  * SearchResultsSteps
  ```java
  public class SearchResultsStep {
        private SearchResultsPage searchResultsPage = new SearchResultsPage();
    
        public SearhResultsSteps verifyThatTopValueIsCorrect(String expectedValue) {
            searchResultsPage.assertThatExpectedValueIsOnSearchTop(expectedValue);
            return this;
        }
  }
  ```
  * SearchSteps
  ```java
  public class SearchSteps {
        private SearchPage searchPage = new SearchPage();
        public SearchResultSteps searchByKeywords(String keyword) {
        searchPage.fillTheSearchFeld(keyword);
        searchPage.pressEnter();
        return new SearchResultSteps();
        }
  }
  ```
  
- tests
  * BaseTest
  ```java

  ```
  * SearchTest
  ```java

  ```
### Пример теста 
```java
   public void searchByKeyWordSeleniumHaveToFindSeleniumhqOrgInTop() {
        step.searchByKeyWord("Selenium")
            .verifyThatTopValueIsCorrect("https://www.seleniumhq.org/")
   }
```


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





