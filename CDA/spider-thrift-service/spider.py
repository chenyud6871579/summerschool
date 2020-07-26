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

    file = open("tem.json", "w", encoding="utf-8")
    try:
        json.dump(json_info, file, ensure_ascii=False)
    except:
        print("1 failed")
        pass

    try:
        json.dump(information.T.to_json(), file, ensure_ascii=False)
    except:
        print("2 failed")
        pass

    my_db[table1].insert(json_info)
    print("保存完成")

    print("正在整理世界疫情信息")
    my_db[table1].delete_many(
        {
            "country": "钻石公主号邮轮"
        }
    )
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
    my_db[table1].update_many(
        {
            "country": "波黑"
        },
        {
            '$set':
            {
                "countryCode": "BA"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "英国属地曼岛"
        },
        {
            '$set':
            {
                "countryCode": "IM"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "格恩西岛"
        },
        {
            '$set':
            {
                "countryCode": "GG"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "泽西岛"
        },
        {
            '$set':
            {
                "countryCode": "JE"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "圣马丁"
        },
        {
            '$set':
            {
                "countryCode": "MF"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "蒙古"
        },
        {
            '$set':
            {
                "countryCode": "MN"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "荷属安的列斯"
        },
        {
            '$set':
            {
                "countryCode": "AN"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "北马里亚纳"
        },
        {
            '$set':
            {
                "countryCode": "MP"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "福克兰群岛（马尔维纳斯）"
        },
        {
            '$set':
            {
                "countryCode": "FK"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "毛利塔尼亚"
        },
        {
            '$set':
            {
                "countryCode": "MR"
            }
        }
    )
    my_db[table1].update_many(
        {
            "country": "纳米比亚"
        },
        {
            '$set':
            {
                "countryCode": "NA"
            }
        }
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
    my_db[table3].delete_many(
        {
            "city": "待确认"
        }
    )
    my_db[table3].delete_many(
        {
            "city": "未公布来源"
        }
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
