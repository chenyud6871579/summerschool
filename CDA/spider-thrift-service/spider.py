import akshare
import pymongo
import json
import requests
from codeChange import codeTable

def get_rate(add, message):
    add_message = {
        "add": add,
        "message": message
    }
    requests.get("http://localhost:8080/progress/spider/add", add_message)


def get_info(ip, db, table1, table2, table3):
    my_client = pymongo.MongoClient(ip)
    my_db = my_client[db]

    print("正在获取新冠疫情信息")
    get_rate(10, "正在获取新冠疫情信息")
    information = akshare.covid_19_history()
    print("获取完成")

    print("正在保存信息到数据库")
    get_rate(20, "正在保存信息到数据库")
    my_db[table1].delete_many({})
    my_db[table2].delete_many({})
    my_db[table3].delete_many({})
    json_info = json.loads(information.T.to_json()).values()
    try:
        print(json_info)
    except:
        print(1)
    try:
        print(json_info[0])
    except:
        print(2)

    # file = open("tem.json", "w", encoding="utf-8")
    # try:
    #     json.dump(json_info, file, ensure_ascii=False)
    # except:
    #     print("1 failed")
    #     pass
    #
    # try:
    #     json.dump(information.T.to_json(), file, ensure_ascii=False)
    # except:
    #     print("2 failed")
    #     pass

    my_db[table1].insert(json_info)
    print("保存完成")

    print("正在清洗世界疫情信息")
    get_rate(10, "正在清洗世界疫情信息")
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
    print("清洗完成")

    print("正在清洗中国疫情信息")
    get_rate(10, "正在清洗中国疫情信息")
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
    print("清洗完成")

    print("正在清洗北京疫情信息")
    get_rate(10, "正在清洗北京疫情信息")
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
    print("清洗完成")

    print("正在整理世界疫情信息")
    get_rate(10, "正在整理世界疫情信息")
    my_db["Globe_new"].delete_many({})
    my_db["Globe_new"].insert(my_db[table1].aggregate([
        {
            '$sort': {"date": 1, "country": 1}
        },
        {
            '$group':
            {
                "_id": "$country",
                "name": {'$first': "$countryCode"},
                "date": {'$push': "$date"},
                "confirmed": {'$push': "$confirmed"},
                "suspected": {'$push': "$suspected"},
                "cured": {'$push': "$cured"},
                "dead": {'$push': "$dead"}
            }
        }
    ]))

    globe_db = my_db["Globe_new"]
    code_map = codeTable
    info = globe_db.find()
    for item in info:
        current_name = item['name']
        if current_name in code_map.keys():
            now_name = code_map[current_name]
            globe_db.update({"name":current_name},{"$set":{"name":now_name}})
            print(current_name + "  替换为  " + now_name)
        else:
            print(current_name + "不存在")
    print("整理完成")

    print("正在整理中国疫情信息")
    get_rate(10, "正在整理中国疫情信息")
    my_db["China_new"].delete_many({})
    my_db["China_new"].insert(my_db[table2].aggregate([
        {
            '$sort': {"date": 1, "province": 1}
        },
        {
            '$group':
            {
                "_id": "$province",
                "date": {'$push': "$date"},
                "confirmed": {'$push': "$confirmed"},
                "suspected": {'$push': "$suspected"},
                "cured": {'$push': "$cured"},
                "dead": {'$push': "$dead"},
            }
        }
    ]))


    print("整理完成")

    print("正在整理北京疫情信息")
    get_rate(10, "正在整理北京疫情信息")
    my_db["Beijing_new"].delete_many({})
    my_db["Beijing_new"].insert(my_db[table3].aggregate([
        {
            '$sort': {"date": 1, "city": 1}
        },
        {
            '$group':
            {
                "_id": "$city",
                "date": {'$push': "$date"},
                "confirmed": {'$push': "$confirmed"},
                "suspected": {'$push': "$suspected"},
                "cured": {'$push': "$cured"},
                "dead": {'$push': "$dead"},
            }
        }
    ]))
    get_rate(10, "获取完成")
    print("整理完成")
