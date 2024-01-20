# %%
import time
import random
import datetime
from selenium import webdriver
from selenium.webdriver.chrome.service import Service as ChromeService
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys

BASE_URL = 'http://localhost:8080/'


# %%
def login():
  print('Login')
  username_field = driver.find_elements(by=By.CSS_SELECTOR, value='#username')
  password_field = driver.find_elements(by=By.CSS_SELECTOR, value='#password')
  username_field[0].send_keys(
    'myusername')  # replace with your username
  password_field[0].send_keys('mypassword')  # replace with your password
  password_field[0].submit()
  assert_not_error()


def logout():
  print('Logout')
  driver.get(BASE_URL + 'logout')


def assert_not_error():
  assert driver.title != "Internal server error"


def click_search():
  search_button = driver.find_element(by=By.XPATH, value="//button[normalize-space()='Search']")
  search_button.click()
  assert_not_error()


def test_search():
  print('Test search')
  driver.get(BASE_URL)
  click_search()

# %%
driver = webdriver.Chrome(
  service=ChromeService(ChromeDriverManager().install()))
driver.get(BASE_URL)
login()

# %%
test_search()

# %%
logout()

# %%
driver.quit()
print('Test completed')
# %%
