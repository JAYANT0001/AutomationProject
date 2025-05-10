#  Google Form Automation using Selenium, TestNG & Java

##  Overview

This project automates the process of filling out a Google Form using **Selenium WebDriver**, **TestNG**, and **Java**. It demonstrates end-to-end form interaction, dynamic data handling, and best practices using reusable wrappers.
---
## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Gradle
- Log4j (for logging)
- Git
---
## ✅ Test Case Details (`testCase01`)

The test automates the following steps:

1. Navigate to the Google Form.
2. Fill in `Crio Learner` in the first text box.
3. Enter the statement:  
   `I want to be the best QA Engineer! <epoch_time>`  
   *(Epoch time is dynamically generated at runtime)*
4. Select your Automation Testing experience from the radio button.
5. Choose **Java**, **Selenium**, and **TestNG** in the checkbox options.
6. Choose your preferred way to be addressed from the dropdown.
7. Input the current date minus 7 days *(calculated dynamically)*.
8. Provide the time as `07:30`.
9. Submit the form.
10. Print the success message shown on the submission screen to the console.

---

## 🔁 Wrapper Utility

All actions like click, enter text, select dropdown, etc., are implemented using a **custom wrapper class** to promote reusability and cleaner code.

---

## 🧩 Challenges Faced

- Handling dynamic input like current epoch time and calculated dates.
- Avoiding CAPTCHAs *(can be worked around using `Thread.sleep()` during execution if triggered)*.
- Dealing with dynamic UI elements on Google Forms.

---


## 🧾 Notes

- Captchas might appear during automation; wait and manually clear them when they do.
- Form selectors might change—ensure the correct XPath or CSS selectors are used.
- Test output and logs are managed via **Log4j**, but additional configuration may be needed for console logging.
