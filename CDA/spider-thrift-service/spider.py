import akshare
import pymongo
import json


def get_info(ip, db, table1, table2, table3):
    my_client = pymongo.MongoClient(ip)
    my_db = my_client[db]

    print("正在获取新冠疫情信息")
    information = akshare.covid_19_history()
    print("获取完成")

    print("正在保存信息到数据库")
    my_db[table1].delete_many({})
    my_db[table2].delete_many({})
    my_db[table3].delete_many({})
    json_info = json.loads(information.T.to_json()).values()
    my_db[table1].insert(json_info)
    print("保存完成")

    print("正在整理世界疫情信息")
    my_db[table1].delete_many({"country": "钻石公主号邮轮"})
    my_db[table1].delete_many(
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
    my_db[table1].update(
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
    print("整理完成")

    print("正在整理中国疫情信息")
    my_db[table2].insert(
        my_db[table1].find(
            {
                "province":
                {
                    '$ne': None
                }
            }
        )
    )
    my_db[table2].update(
        {},
        {
            '$unset':
            {
                "country": "",
                "countryCode": ""
            }
        },
        upsert=False,
        multi=True
    )
    my_db[table1].delete_many(
        {
            "province":
            {
                '$ne': None
            }
        }
    )
    my_db[table1].update(
        {},
        {
            '$unset':
            {
                "province": "",
                "city": ""
            }
        },
        upsert=False,
        multi=True
    )
    print("整理完成")

    print("正在整理北京疫情信息")
    my_db[table3].insert(
        my_db[table2].find(
            {
                "city":
                {
                    '$ne': None
                }
            }
        )
    )
    my_db[table3].update(
        {},
        {
            '$unset':
            {
                "province": ""
            }
        },
        upsert=False,
        multi=True
    )
    my_db[table2].delete_many(
        {
            "city":
            {
                '$ne': None
            }
        }
    )
    my_db[table2].update(
        {},
        {
            '$unset':
            {
                "city": ""
            }
        },
        upsert=False,
        multi=True
    )
    print("整理完成")
