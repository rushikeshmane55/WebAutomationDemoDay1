package ccst.Day6;

import com.microsoft.playwright.Locator;

public class CustomAssertions {
    private final Locator locator;

    private CustomAssertions(Locator locator) {
        this.locator = locator;
    }

    public static CustomAssertions assertThat(Locator locator) {
        return new CustomAssertions(locator);
    }

    public CustomAssertions hasCssClass(String className) {
        String actualClass = locator.getAttribute("class");
        System.out.println("In Custom Assertion");
        System.out.println("Locator: " + locator.toString());

        if (actualClass == null || !actualClass.contains(className)) {
            throw new AssertionError("Expected element to have class: " + className + ", but found: " + actualClass);
        }
        return this;
    }

    public CustomAssertions hasExactTxt(String exactText) {
        String actualTxt = locator.innerText();
        System.out.println("In Custom Txt Assertion");
        System.out.println("Locator: " + locator.toString());

        if (actualTxt == null || !actualTxt.trim().equals(exactText.trim())) {
            throw new AssertionError("Expected element to have text '" + exactText + "', but found: '" + actualTxt + "'");
        }

        return this;
    }
}