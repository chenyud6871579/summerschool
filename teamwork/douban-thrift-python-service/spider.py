import requests
import time
import math
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options

requests_headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
                                  "AppleWebKit/537.36 (KHTML, like Gecko) "
                                  "Chrome/83.0.4103.116 Safari/537.36"}

main_page_url = "https://movie.douban.com/explore#!type=movie&tag=%E8%B1%86%E7%93%A3%E9%AB%98%E5%88%86" \
                "&sort=recommend&page_limit=20&page_start=0"


def get_detail(id):
    id = str(id)
    url = "https://movie.douban.com/subject/" + id
    web_data = requests.get(url, headers=requests_headers)
    soup = BeautifulSoup(web_data.text, "html.parser")

    # 电影标题
    title_info = soup.select_one("#content > h1 > span:nth-child(1)").text
    title = title_info.split()[0]

    # 电影评分
    score = soup.select_one("#interest_sectl > div.rating_wrap.clearbox > div.rating_self.clearfix > strong").text

    # 电影评论数量
    comment_info = soup.select_one("#comments-section > div.mod-hd > h2 > span > a").text
    comment_num = comment_info.split()[1]

    return title, score, comment_num


def get_list(url, pages):
    id_list = []
    chrome_options = Options()
    chrome_options.add_argument('--headless')
    driver = webdriver.Chrome(executable_path=r"D:\chromedriver.exe", options=chrome_options)
    driver.get(url)
    time.sleep(1)

    get_more_btn = driver.find_element(By.CSS_SELECTOR, "#content > div > div.article > div > div.list-wp > a")
    for i in range(pages):
        get_more_btn.click()
        time.sleep(1)

    movies = driver.find_elements(By.CSS_SELECTOR, "#content > div > div.article > div > div.list-wp > div > a")
    for item in movies:
        id = item.find_element(By.TAG_NAME, "div").get_attribute("data-id")
        id_list.append(id)

    driver.quit()
    return id_list


def get_info(num):
    pages_num = math.ceil(num / 20)
    id_list = get_list(main_page_url, pages_num)
    information = []
    print("开始抓取豆瓣高分电影")

    for index in range(num):
        id = id_list[index]
        information.append(get_detail(id))
        print("已完成" + str((index + 1) * 100 / num) + "%")

    for index in range(num):
        print(information[index])
        # print(information[index][0])
        # print(information[index][1])
        # print(information[index][2])


if __name__ == "__main__":
    get_info(3)
