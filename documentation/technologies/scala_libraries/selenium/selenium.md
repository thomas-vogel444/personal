# Selenium

From https://www.scalatest.org/user_guide/using_selenium

## Set up 

Add `"org.scalatestplus" %% "selenium-2-45" % "3.1.1.0" % Test` to your build

The whole DSL lives in the trait `org.scalatestplus.selenium.WebBrowser`. 

Additionally you need a `WebDriver`, e.g. `org.openqa.selenium.htmlunit.HtmlUnitDriver`

## Navigation

- Ask the browser to retrieve the page
```scala
go to "http://www.some-url.com"
```

## Getting and setting input elements values

- Text fields and text areas
```scala
textField("q").value = "Cheese!"
textArea("body").value = "whatever"

textField("q").value should be ("Cheese!")
```

- Radio buttons
```
radioButtonGroup("group1").value = "Option2"
// OR 
radioButtonGroup("group1").selection = Some("Option2")
```

- Checkboxes
```scala
checkbox("cbx1").select()
checkbox("cbx1").clear()

checkbox("cbx1").isSelected should be (true)
```

- Single-selection dropdown lists
```scala
singleSel("select1").value = "option2"
// OR 
singleSel("select1").selection = Some("option2")

singleSel.clear()
// OR
singleSel("select1").selection = None  
```

- Multiple-selection lists

- Clicking and submitting
```scala
click on id("q")
```
`id` can be replaced by `name`, `xpath`, `className`, `cssSelector`, `linkText`, `partialLinkText`, `tagName`

## Switching

## Navigation History

## Cookies

## Implicit Wait

## Page source and current URL

## Screen capture

## Using the page object pattern

## Executing Javascript

## Querying for elements

## Cleaning up

- Closing the current browser window
```scala
close()
```

- Closing all windows and exit the driver
```scala
quit()
```