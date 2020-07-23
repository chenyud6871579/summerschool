import akshare
import pymongo
import json


def get_info(ip, db, table):
    my_client = pymongo.MongoClient(ip)
    my_db = my_client[db]
    my_table = table

    print("开始获取新冠疫情信息")
    information = akshare.covid_19_history()
    print("获取完成")

    print("开始保存信息到数据库")
    my_db[my_table].delete_many({})
    json_info = json.loads(information.T.to_json()).values()
    my_db[my_table].insert(json_info)
    print("保存完成")

    print("开始清洗数据库中的信息")
    my_db[my_table].delete_many({"country": "钻石公主号邮轮"})
    my_db[my_table].delete_many(
        {
            "province":
                {
                    '$nin':
                        [
                            "北京市",
                            None
                        ]
                },
            "city":
                {
                    '$ne': None
                },
        }
    )
    my_db[my_table].update(
        {},
        {
            '$unset':
                {
                    "provinceCode": "",
                    "cityCode": ""
                }
        },
        upsert=False,
        multi=True
    )
    print("清洗完成")
