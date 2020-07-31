var dataMap = {};
var myalldata = {
    "beijing": ['东城区', '西城区', '朝阳区', '丰台区', '石景山区', '海淀区', '门头沟区', '房山区', '通州区', '顺义区', '昌平区', '大兴区',
        '怀柔区', '平谷区', '密云区', '延庆区'],
    "china": ['湖北', '北京', '天津', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '上海', '江苏',
        '浙江', '安徽', '福建', '江西', '山东', '河南', '湖南', '广东', '广西', '海南', '重庆', '四川', '贵州', '云南', '西藏', '陕西', '甘肃',
        '青海', '宁夏', '新疆', '台湾', '香港', '澳门'],
    "globe": ['巴西', '泰国', '斯威士兰', '安道尔', '委内瑞拉', '约旦', '新西兰', '柬埔寨', '布隆迪', '贝宁',
        '加纳', '科特迪瓦', '直布罗陀', '挪威', '阿根廷', '摩尔多瓦', '纳米比亚', '波黑', '圣马丁',
        '东帝汶', '乌兹别克斯坦', '卡塔尔', '韩国', '巴林', '哥伦比亚', '英国', '德国', '马耳他', '圣巴泰勒米',
        '瑞士', '奥地利', '马尔代夫', '意大利', '加蓬', '科摩罗', '土耳其', '芬兰', '阿鲁巴', '多米尼加',
        '圭亚那', '索马里', '叙利亚', '巴巴多斯', '爱尔兰', '阿尔巴尼亚', '中国', '澳大利亚', '蒙古', '佛得角', '巴勒斯坦', '赤道几内亚',
        '阿曼', '巴基斯坦', '北马里亚纳', '卢森堡', '海地', '古巴', '黎巴嫩', '法属圭亚那', '哥斯达黎加', '尼泊尔', '罗马尼亚', '卢旺达',
        '利比里亚', '几内亚', '北马其顿', '安哥拉', '吉布提', '以色列', '开曼群岛', '墨西哥', '危地马拉', '希腊', '阿富汗', '亚美尼亚', '多米尼克',
        '捷克', '百慕大', '新喀里多尼亚', '保加利亚', '匈牙利', '波兰', '法国', '俄罗斯', '斯里兰卡', '荷兰', '斯洛文尼亚', '瑞典', '摩洛哥', '中非',
        '缅甸', '冰岛', '突尼斯', '智利', '乍得', '马拉维', '巴布亚新几内亚', '伯利兹', '南非', '圣多美和普林西比', '马里', '马来西亚', '巴哈马', '荷属安的列斯',
        '葡萄牙', '津巴布韦', '孟加拉国', '菲律宾', '多哥', '赞比亚', '文莱', '克罗地亚', '尼日利亚', '莫桑比克', '泽西岛', '巴拉圭',
        '厄立特里亚', '伊拉克', '莱索托', '越南', '美国', '梵蒂冈', '肯尼亚', '圣皮埃尔和密克隆', '尼加拉瓜', '法属波利尼西亚', '巴拿马', '英属维尔京群岛', '摩纳哥', '埃塞俄比亚', '老挝', '英国属地曼岛', '印度', '圣卢西亚', '塞舌尔', '安提瓜和巴布达', '蒙特塞拉特', '伊朗', '白俄罗斯', '格陵兰',
        '苏丹', '阿尔及利亚', '斯洛伐克', '也门', '尼日尔', '拉脱维亚', '喀麦隆', '印度尼西亚', '法罗群岛', '格鲁吉亚', '关岛', '坦桑尼亚',
        '毛里求斯', '丹麦', '塞尔维亚', '列支敦士登', '波多黎各', '洪都拉斯', '萨尔瓦多', '布基纳法索', '沙特阿拉伯', '秘鲁', '南苏丹', '厄瓜多尔', '玻利维亚',
        '马约特', '阿联酋', '格恩西岛', '塞浦路斯', '圣马力诺', '不丹', '博茨瓦纳', '马提尼克', '几内亚比绍', '哈萨克斯坦', '塞拉利昂', '阿塞拜疆',
        '黑山', '西班牙', '乌克兰', '乌拉圭', '苏里南', '安圭拉', '塔吉克斯坦', '乌干达', '加拿大', '日本', '爱沙尼亚', '马达加斯加', '冈比亚', '新加坡',
        '塞内加尔', '科威特', '牙买加', '毛利塔尼亚', '格陵兰', '莱索托', '刚果金', '立陶宛', '埃及', '孟加拉', '吉尔吉斯斯坦', '刚果布', '利比亚', '比利时', '阿拉伯联合酋长国'
    ]
}

var geoObject = {
    "beijing": {
        '东城区': [116.42, 39.93], '西城区': [116.37, 37.92], '崇文区': [116.43, 39.88], '宣武区': [116.35, 39.86],
        '朝阳区': [116.43, 39.92], '丰台区': [116.28, 39.82], '石景山区': [116.22, 39.9], '海淀区': [116.3, 39.95], '门头沟区': [116.1, 39.93],
        '房山区': [116.13, 39.75], '通州区': [116.65, 39.92], '顺义区': [116.65, 40.13], '昌平区': [116.23, 40.22], '大兴区': [116.33, 39.73],
        '怀柔区': [116.63, 40.32], '平谷区': [117.12, 40.13], '密云区': [116.83, 40.37], '延庆区': [115.97, 40.45],
    },
    "china": {
        '西藏': [91.11, 29.97], '上海': [121.48, 31.22], '福建': [119.3, 26.08], '浙江': [121.56, 29.86], '广西': [108.33, 22.84],
        '广东': [113.23, 23.16], '云南': [102.73, 25.04], '海南': [110.35, 20.02], '辽宁': [123.38, 41.8], '吉林': [125.35, 43.88], '宁夏': [106.27, 38.47],
        '江西': [115.89, 28.68], '吉林': [126.57, 43.87], '青海': [95.74, 40.56], '内蒙古': [111.65, 40.82], '四川': [104.06, 30.67],
        '山西': [113.3, 40.12], '陕西': [108.95, 34.27], '浙江': [120.58, 30.01], '重庆': [106.54, 29.59], '江苏': [118.78, 32.04],
        '贵州': [106.71, 26.57], '北京': [116.46, 39.92], '新疆': [87.68, 43.77], '甘肃': [103.73, 36.03], '天津': [117.2, 39.13],
        '河南': [113.65, 34.76], '黑龙江': [126.63, 45.75], '湖南': [113, 28.21], '安徽': [117.27, 31.86], '湖北': [114.31, 30.52],
        '河北': [114.48, 38.03], '台湾': [119.2, 20.4], '澳门': [114, 23], '香港': [113, 22],
    },
    "globe": {
        '阿拉伯联合酋长国': [54.37, 24.28], '澳大利亚': [149.08, -35.15], '比利时': [4.21, 50.57], '柬埔寨': [104.55, 11.33], '加拿大': [-75.42, 45.27],
        '中国': [116.2, 39.55], '埃及': [31.34, 30.01], '芬兰': [25.03, 60.15], '法国': [2.2, 48.5], '德国': [13.25, 52.3], '印度': [71.13, 28.37],
        '意大利': [12.29, 41.54], '马来西亚': [101.43, 3.09], '尼泊尔': [85.2, 27.45], '韩国': [126.58, 37.31], '俄罗斯': [37.35, 55.45], '西班牙': [2.173403, 41.385064],
        '泰国': [100.35, 13.45], '美国': [-77.03, 39.91], '英国': [-0.127758, 51.507351], '日本': [139.46, 35.42], '新加坡': [103.49, 1.21],
        '斯里兰卡': [79.52, 6.5], '菲律宾': [121.05, 14.4], '瑞典': [18.05, 59.2], '越南': [105.55, 21.03], "荷兰": [4.895168, 52.370216],
        "雅典": [-83.357567, 33.951935], "奥克兰": [174.763332, -36.84846], "哥伦比亚": [-74.072092, 4.710989], "斯洛伐克": [17.107748, 48.148596],
        "匈牙利": [19.040235, 47.497912], "阿根廷": [-58.381559, -34.603684], "罗马尼亚": [26.102538, 44.426767],
        "委内瑞拉": [-66.903606, 10.480594], "印度": [77.209021, 28.613939], "爱尔兰": [-6.26031, 53.349805], "土耳其": [28.978359, 41.008238],
        "印度尼西亚": [106.845599, -6.208763], "乌克兰": [30.5234, 50.4501], "丹麦": [12.568337, 55.676097], "马来西亚": [101.686855, 3.139003],
        "秘鲁": [-77.042793, -12.046374], "葡萄牙": [-9.139337, 38.722252], "斯洛文尼亚": [14.505751, 46.056947], "卢森堡": [6.129583, 49.815273],
        "苏格兰": [4.835659, 45.764043], "巴林": [50.58605, 26.228516], "墨西哥": [-99.133208, 19.432608], "肯尼亚": [36.821946, -1.292066], "塞浦路斯": [33.382276, 35.185566],
        "挪威": [10.752245, 59.913869], "法国": [2.352222, 48.856614], "捷克": [14.4378, 50.075538], "拉脱维亚": [24.105186, 56.949649],
        "巴西": [-43.172896, -22.906847], "罗马": [12.496366, 41.902783], "希腊": [-70.669265, -33.44889], "保加利亚": [23.321868, 42.697708],
        "爱沙尼亚": [24.753575, 59.436961], "以色列": [34.781768, 32.0853], "立陶宛": [25.279651, 54.687156], "华沙": [21.012229, 52.229676],
        "奥地利": [16.373819, 48.208174], "瑞士": [8.541694, 47.376887]
    }
}

var geobeijing = {
    '东城区': [116.42, 39.93], '西城区': [116.37, 37.92], '崇文区': [116.43, 39.88], '宣武区': [116.35, 39.86],
    '朝阳区': [116.43, 39.92], '丰台区': [116.28, 39.82], '石景山区': [116.22, 39.9], '海淀区': [116.3, 39.95], '门头沟区': [116.1, 39.93],
    '房山区': [116.13, 39.75], '通州区': [116.65, 39.92], '顺义区': [116.65, 40.13], '昌平区': [116.23, 40.22], '大兴区': [116.33, 39.73],
    '怀柔区': [116.63, 40.32], '平谷区': [117.12, 40.13], '密云区': [116.83, 40.37], '延庆区': [115.97, 40.45],
}
var geozhongguo = {
    '西藏': [91.11, 29.97], '上海': [121.48, 31.22], '福建': [119.3, 26.08], '浙江': [121.56, 29.86], '广西': [108.33, 22.84],
    '广东': [113.23, 23.16], '云南': [102.73, 25.04], '海南': [110.35, 20.02], '辽宁': [123.38, 41.8], '吉林': [125.35, 43.88], '宁夏': [106.27, 38.47],
    '江西': [115.89, 28.68], '吉林': [126.57, 43.87], '青海': [95.74, 40.56], '内蒙古': [111.65, 40.82], '四川': [104.06, 30.67],
    '山西': [113.3, 40.12], '陕西': [108.95, 34.27], '浙江': [120.58, 30.01], '重庆': [106.54, 29.59], '江苏': [118.78, 32.04],
    '贵州': [106.71, 26.57], '北京': [116.46, 39.92], '新疆': [87.68, 43.77], '甘肃': [103.73, 36.03], '天津': [117.2, 39.13],
    '河南': [113.65, 34.76], '黑龙江': [126.63, 45.75], '湖南': [113, 28.21], '安徽': [117.27, 31.86], '湖北': [114.31, 30.52],
    '河北': [114.48, 38.03], '台湾': [119.2, 20.4], '澳门': [114, 23], '香港': [113, 22],
}
var geoworld = {
    '阿拉伯联合酋长国': [54.37, 24.28], '澳大利亚': [149.08, -35.15], '比利时': [4.21, 50.57], '柬埔寨': [104.55, 11.33], '加拿大': [-75.42, 45.27],
    '中国': [116.2, 39.55], '埃及': [31.34, 30.01], '芬兰': [25.03, 60.15], '法国': [2.2, 48.5], '德国': [13.25, 52.3], '印度': [71.13, 28.37],
    '意大利': [12.29, 41.54], '马来西亚': [101.43, 3.09], '尼泊尔': [85.2, 27.45], '韩国': [126.58, 37.31], '俄罗斯': [37.35, 55.45], '西班牙': [2.173403, 41.385064],
    '泰国': [100.35, 13.45], '美国': [-77.03, 39.91], '英国': [-0.127758, 51.507351], '日本': [139.46, 35.42], '新加坡': [103.49, 1.21],
    '斯里兰卡': [79.52, 6.5], '菲律宾': [121.05, 14.4], '瑞典': [18.05, 59.2], '越南': [105.55, 21.03], "荷兰": [4.895168, 52.370216],
    "雅典": [-83.357567, 33.951935], "奥克兰": [174.763332, -36.84846], "哥伦比亚": [-74.072092, 4.710989], "斯洛伐克": [17.107748, 48.148596],
    "匈牙利": [19.040235, 47.497912], "阿根廷": [-58.381559, -34.603684], "罗马尼亚": [26.102538, 44.426767],
    "委内瑞拉": [-66.903606, 10.480594], "印度": [77.209021, 28.613939], "爱尔兰": [-6.26031, 53.349805], "土耳其": [28.978359, 41.008238],
    "印度尼西亚": [106.845599, -6.208763], "乌克兰": [30.5234, 50.4501], "丹麦": [12.568337, 55.676097], "马来西亚": [101.686855, 3.139003],
    "秘鲁": [-77.042793, -12.046374], "葡萄牙": [-9.139337, 38.722252], "斯洛文尼亚": [14.505751, 46.056947], "卢森堡": [6.129583, 49.815273],
    "苏格兰": [4.835659, 45.764043], "巴林": [50.58605, 26.228516], "墨西哥": [-99.133208, 19.432608], "肯尼亚": [36.821946, -1.292066], "塞浦路斯": [33.382276, 35.185566],
    "挪威": [10.752245, 59.913869], "法国": [2.352222, 48.856614], "捷克": [14.4378, 50.075538], "拉脱维亚": [24.105186, 56.949649],
    "巴西": [-43.172896, -22.906847], "罗马": [12.496366, 41.902783], "希腊": [-70.669265, -33.44889], "保加利亚": [23.321868, 42.697708],
    "爱沙尼亚": [24.753575, 59.436961], "以色列": [34.781768, 32.0853], "立陶宛": [25.279651, 54.687156], "华沙": [21.012229, 52.229676],
    "奥地利": [16.373819, 48.208174], "瑞士": [8.541694, 47.376887]

}

var myurls = {
    "beijing": "/data?type=beijing",
    "china": "/data?type=china",
    "globe": "/data?type=globe"
}
var colorTitle = {
    "beijing": "北京市各区新冠肺炎确诊人数分布",
    "china": "全国各省市新冠肺炎确诊人数分布",
    "globe": "世界各国新冠肺炎确诊人数分布"
}
var pointTitle = {
    "beijing": "北京市各区新冠肺炎确诊人数分布散点图",
    "china": "全国各省市新冠肺炎确诊人数分布散点图",
    "globe": "世界各国新冠肺炎确诊人数分布散点图"
}
var mapTypeObject = {
    "beijing": "北京",
    "china": "china",
    "globe": "world"
}

var pList = []
var geoCoordMap = []

var convertData = function (data, typeFlag) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        // console.log("data",data)
        var geoCoord = geoObject[typeFlag][data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value),
                vec: data[i].value
            });
        }
    }
    return res;
}

function timedata() {
    var date = []
    for (var i = 1; i < 31; i++) {
        date[i-1] = "04-" + String(i)
    }
    for (var i = 1; i < 32; i++) {
        date[i+29] = "05-" + String(i)
    }
    for (var j = 1; j < 31; j++) {
        date[j+60] = "06" + "-" + String(j)
    }
    for (var j = 1; j < 32; j++) {
        date[j+90] = "07" + "-" + String(j)
    }
    return date
}

function dataFormatter(obj) {
    var temp;
    for (var num = 1; num <= 121; num++) {
        temp = obj[num];
        for (var i = 0, l = temp.length; i < l; i++) {
            obj[num][i] = {
                name: pList[i],
                value: temp[i],
            }
        }
    }
    return obj;
}

var getResultData;

function myshow(boxId, typeFlag, buttonFlag) {
    var myChart = echarts.init(document.getElementById(boxId));

    pList = myalldata[typeFlag]

    $.ajax({
        type: "get",
        url: myurls[typeFlag],
        dataType: "json",
        success: function (result) {
            getResultData = result;
            var obj = []
            // var data1 = []
            var resultOption;
            for (var i = 0; i < 122; i++) {
                var confirmed = []
                for (var item in pList) {
                    var proviceName = pList[item];
                    if (proviceName == "内蒙古") {
                        proviceName = "内蒙古自治区"
                    } else if (proviceName == "湖北") {
                        proviceName = "湖北省"
                    } else if (proviceName == "天津") {
                        proviceName = "天津市"
                    } else if (proviceName == "河北") {
                        proviceName = "河北省"
                    } else if (proviceName == "山西") {
                        proviceName = "山西省"
                    } else if (proviceName == "辽宁") {
                        proviceName = "辽宁省"
                    } else if (proviceName == "吉林") {
                        proviceName = "吉林省"
                    } else if (proviceName == "黑龙江") {
                        proviceName = "黑龙江省"
                    } else if (proviceName == "上海") {
                        proviceName = "上海市"
                    } else if (proviceName == "浙江") {
                        proviceName = "浙江省"
                    } else if (proviceName == "安徽") {
                        proviceName = "安徽省"
                    } else if (proviceName == "福建") {
                        proviceName = "福建省"
                    } else if (proviceName == "江西") {
                        proviceName = "江西省"
                    } else if (proviceName == "山东") {
                        proviceName = "山东省"
                    } else if (proviceName == "河南") {
                        proviceName = "河南省"
                    } else if (proviceName == "湖南") {
                        proviceName = "湖南省"
                    } else if (proviceName == "广东") {
                        proviceName = "广东省"
                    } else if (proviceName == "广西") {
                        proviceName = "广西壮族自治区"
                    } else if (proviceName == "海南") {
                        proviceName = "海南省"
                    } else if (proviceName == "重庆") {
                        proviceName = "重庆市"
                    } else if (proviceName == "四川") {
                        proviceName = "四川省"
                    } else if (proviceName == "贵州") {
                        proviceName = "贵州省"
                    } else if (proviceName == "云南") {
                        proviceName = "云南省"
                    } else if (proviceName == "西藏") {
                        proviceName = "西藏自治区"
                    } else if (proviceName == "陕西") {
                        proviceName = "陕西省"
                    } else if (proviceName == "甘肃") {
                        proviceName = "甘肃省"
                    } else if (proviceName == "青海") {
                        proviceName = "青海省"
                    } else if (proviceName == "宁夏") {
                        proviceName = "宁夏回族自治区"
                    } else if (proviceName == "新疆") {
                        proviceName = "新疆维吾尔自治区"
                    } else if (proviceName == "台湾") {
                        proviceName = "台湾省"
                    } else if (proviceName == "香港") {
                        proviceName = "香港特别行政区"
                    } else if (proviceName == "澳门") {
                        proviceName = "澳门特别行政区"
                    } else if (proviceName == "北京") {
                        proviceName = "北京市"
                    } else if (proviceName == "江苏") {
                        proviceName = "江苏省"
                    } if (proviceName == "刚果金") {
                        proviceName = "刚果（金）"
                    }
                    if (proviceName == "毛里塔尼亚") {
                        proviceName = "毛利塔尼亚"
                    } if (proviceName == "孟加拉") {
                        proviceName = "孟加拉国"
                    } if (proviceName == "吉尔吉斯坦") {
                        proviceName = "吉尔吉斯斯坦"
                    } if (proviceName == "刚果布") {
                        proviceName = "刚果（布）"
                    } if (proviceName == "阿拉伯联合酋长国") {
                        proviceName = "阿联酋"
                    }
                    if(result[proviceName]["confirmed"].length < 123){
                        confirmed.push(0)
                    }
                    else {
                        var index = result[proviceName]["confirmed"].length - 122 + i
                        confirmed.push(result[proviceName]["confirmed"][index])
                    }
                }
                obj[String(i)] = confirmed
            }
            dataMap.confirm = dataFormatter(obj)

            if (buttonFlag == "color") {
                var resultOption = {
                    baseOption: {
                        timeline: {
                            axisType: 'category',
                            autoPlay: false,
                            playInterval: 75,
                            data: timedata(),
                            loop: false,
                            label: {
                                normal: {
                                    textStyle: {
                                        color: '#ddd'
                                    }
                                },
                                emphasis: {
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            tooltip: {
                                padding: 10,
                                backgroundColor: '#222',
                                borderColor: '#777',
                                borderWidth: 1,
                                trigger: 'item',

                                formatter: function (params) {
                                    return params.name;
                                }
                            },
                        },

                        title: {
                            text: colorTitle[typeFlag],
                            subtext: '4月初至7月底数据',

                            left: 'center',
                            textStyle: {
                                color: '#fff',
                                fontSize: 25
                            }
                        },

                        itemStyle: {

                            normal: {
                                areaColor: '#C0C0C0',
                                borderColor: '#003366'
                            },
                            emphasis: {
                                areaColor: '#8ad216',//鼠标选择区域颜色
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                shadowBlur: 20,
                                borderWidth: 0,
                                shadowColor: '#03CCFF',

                            }
                        },
                        tooltip: {
                            // padding: 10,
                            // backgroundColor: '#222',
                            // borderColor: '#777',
                            // borderWidth: 1,
                            // trigger: 'item',
                            formatter: function (params) {
                                return params.name + '确诊病例:' + params.value;
                            }
                        },
                        calculable: true,
                        visualMap: {
                            min: 0,
                            max: 3000000,
                            left: 26,
                            bottom: 40,
                            showLabel: !0,
                            text: ["高", "低"],
                            textStyle: {
                                color: '#00000'
                            },
                            pieces: [{
                                gt: 2000000,
                                label: "> 2000000 人",
                                color: "#660000"
                            }, {
                                gte: 500000,
                                lte: 2000000,
                                label: "500000 - 2000000 人",
                                color: "#990000"
                            }, {
                                gte: 100000,
                                lte: 500000,
                                label: "100000 - 500000 人",
                                color: "#993300"
                            }, {
                                gte: 50000,
                                lte: 100000,
                                label: "50000 - 100000 人",
                                color: "#CC0000"
                            }, {
                                gte: 10000,
                                lte: 50000,
                                label: "10000 - 50000 人",
                                color: "#FF3333"
                            }, {
                                gte: 2000,
                                lt: 10000,
                                label: "2000 - 10000 人",
                                color: "#FF6633"
                            }, {
                                gte: 600,
                                lte: 2000,
                                label: "600 - 2000 人",
                                color: "#FF9900"
                            }, {
                                gte: 300,
                                lte: 600,
                                label: "300 - 600 人",
                                color: "#FF9966"
                            },
                            {
                                gte: 100,
                                lte: 300,
                                label: "100 - 300 人",
                                color: "#FFCC33"
                            }, {
                                gte: 10,
                                lt: 100,
                                label: "10 - 100 人",
                                color: "#f1c40f"
                            }, {
                                gt: 0,
                                lt: 9,
                                label: "1-9人",
                                color: "#FFFF99"
                            }, {
                                value: 0,
                                color: "#ffffff"
                            }],
                            show: !0
                        },
                    },
                    options: []
                }
                for (var i = 1; i < 121; i++) {
                    resultOption.options.push({
                        series: {
                            type: 'map',
                            mapType: mapTypeObject[typeFlag],
                            data: obj[i],
                            roam: true
                        }
                    })
                }
            }

            else if (buttonFlag == "point") {
                resultOption = {
                    animation: true,
                    animationDuration: 1000,
                    animationEasing: 'cubicInOut',
                    animationDurationUpdate: 1000,
                    animationEasingUpdate: 'cubicInOut',
                    grid: {
                        right: '1%',
                        top: '15%',
                        bottom: '10%',
                        width: '20%'
                    },
                    backgroundColor: '#14c9ea',
                    timeline: {
                        axisType: 'category',
                        // realtime: false,
                        loop: false,
                        autoPlay: false,
                        // currentIndex: 2,
                        label: {
                            normal: {
                                textStyle: {
                                    color: '#ddd'
                                }
                            },
                            emphasis: {
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        playInterval: 75,
                        // controlStyle: {
                        //     position: 'left'
                        // },
                        textStyle: {
                            color: '#fff'
                        },
                        data: timedata(),
                        tooltip: {
                            padding: 10,
                            backgroundColor: '#222',
                            borderColor: '#777',
                            borderWidth: 1,
                            trigger: 'item',
                            formatter: function (params) {
                                return params.name;
                            }
                        },
                    },

                    baseOption: {
                        title: {
                            text: pointTitle[typeFlag],
                            subtext: '4月初至7月底数据',

                            left: 'center',
                            textStyle: {
                                fontSize: 25,
                                color: '#fff'
                            }
                        },
                        series: [
                        ],
                        geo: {
                            map: mapTypeObject[typeFlag],
                            roam: true,//不开启缩放和平移
                            zoom: 1,//视角缩放比例
                            label: {
                                normal: {
                                    show: false,
                                    fontSize: '12',
                                    color: '#fff',
                                    itemStyle: {
                                        normal: {
                                            areaStyle: { color: '#fff' }
                                        }
                                    }
                                }
                            },
                            itemStyle: {

                                normal: {
                                    areaColor: '#C0C0C0',
                                    borderColor: '#003366'
                                },
                                emphasis: {
                                    areaColor: '#03b329',//鼠标选择区域颜色
                                    shadowOffsetX: 10,
                                    shadowOffsetY: 10,
                                    shadowBlur: 20,
                                    borderWidth: 0,
                                    shadowColor: '#03CCFF',

                                }
                            }
                        },

                        // itemStyle: {
                        //
                        //     normal: {
                        //         areaColor: '#777',
                        //         borderColor: '#003366'
                        //     },
                        //     emphasis: {
                        //         areaColor: '#F3B329',//鼠标选择区域颜色
                        //         shadowOffsetX: 0,
                        //         shadowOffsetY: 0,
                        //         shadowBlur: 20,
                        //         borderWidth: 0,
                        //         shadowColor: '#33CCFF',
                        //
                        //     }
                        // },
                        tooltip: {
                            // padding: 10,
                            // backgroundColor: '#222',
                            // borderColor: '#222',
                            // borderWidth: 1,
                            // trigger: 'item',
                            formatter: function (params) {
                                return params.name + '确诊病例:' + params.value[2]

                            }
                        },
                        visualMap: {
                            min: 0,
                            max: 2500,
                            left: 'left',
                            top: 'bottom',
                            text: ['高', '低'],           // 文本，默认为数值文本
                            calculable: true,
                            inRange: {
                                color: ['#e2fdf8', '#cff1ea']

                            },
                        },
                    },
                    options: []
                }
                for (var i = 1; i < 122; i++) {
                    resultOption.options.push({
                        series: {
                            name: '信息量',

                            geoIndex: 0,
                            coordinateSystem: 'geo',
                            type: 'scatter',
                            data: convertData(obj[i], typeFlag),
                            roam: true,

                            showAllSymbol: true,

                            symbolSize: function (val) {
                                // console.log(val)
                                if(typeFlag == "globe")
                                    return Math.pow(val[2],1/2)/25
                                else if(typeFlag == "china")
                                    return Math.pow(val[2],1/2)/4
                                else return Math.pow(val[2],1/2)*2

                            },

                            label: {
                                textStyle: {
                                    fontSize: 20
                                }
                            },
                        }


                    })
                }

            }

            function changeName(proviceName){
                if (proviceName == "内蒙古") {
                    proviceName = "内蒙古自治区"
                } else if (proviceName == "湖北") {
                    proviceName = "湖北省"
                } else if (proviceName == "天津") {
                    proviceName = "天津市"
                } else if (proviceName == "河北") {
                    proviceName = "河北省"
                } else if (proviceName == "山西") {
                    proviceName = "山西省"
                } else if (proviceName == "辽宁") {
                    proviceName = "辽宁省"
                } else if (proviceName == "吉林") {
                    proviceName = "吉林省"
                } else if (proviceName == "黑龙江") {
                    proviceName = "黑龙江省"
                } else if (proviceName == "上海") {
                    proviceName = "上海市"
                } else if (proviceName == "浙江") {
                    proviceName = "浙江省"
                } else if (proviceName == "安徽") {
                    proviceName = "安徽省"
                } else if (proviceName == "福建") {
                    proviceName = "福建省"
                } else if (proviceName == "江西") {
                    proviceName = "江西省"
                } else if (proviceName == "山东") {
                    proviceName = "山东省"
                } else if (proviceName == "河南") {
                    proviceName = "河南省"
                } else if (proviceName == "湖南") {
                    proviceName = "湖南省"
                } else if (proviceName == "广东") {
                    proviceName = "广东省"
                } else if (proviceName == "广西") {
                    proviceName = "广西壮族自治区"
                } else if (proviceName == "海南") {
                    proviceName = "海南省"
                } else if (proviceName == "重庆") {
                    proviceName = "重庆市"
                } else if (proviceName == "四川") {
                    proviceName = "四川省"
                } else if (proviceName == "贵州") {
                    proviceName = "贵州省"
                } else if (proviceName == "云南") {
                    proviceName = "云南省"
                } else if (proviceName == "西藏") {
                    proviceName = "西藏自治区"
                } else if (proviceName == "陕西") {
                    proviceName = "陕西省"
                } else if (proviceName == "甘肃") {
                    proviceName = "甘肃省"
                } else if (proviceName == "青海") {
                    proviceName = "青海省"
                } else if (proviceName == "宁夏") {
                    proviceName = "宁夏回族自治区"
                } else if (proviceName == "新疆") {
                    proviceName = "新疆维吾尔自治区"
                } else if (proviceName == "台湾") {
                    proviceName = "台湾省"
                } else if (proviceName == "香港") {
                    proviceName = "香港特别行政区"
                } else if (proviceName == "澳门") {
                    proviceName = "澳门特别行政区"
                } else if (proviceName == "北京") {
                    proviceName = "北京市"
                } else if (proviceName == "江苏") {
                    proviceName = "江苏省"
                } if (proviceName == "刚果金") {
                    proviceName = "刚果（金）"
                }
                if (proviceName == "毛里塔尼亚") {
                    proviceName = "毛利塔尼亚"
                } if (proviceName == "孟加拉") {
                    proviceName = "孟加拉国"
                } if (proviceName == "吉尔吉斯坦") {
                    proviceName = "吉尔吉斯斯坦"
                } if (proviceName == "刚果布") {
                    proviceName = "刚果（布）"
                } if (proviceName == "阿拉伯联合酋长国") {
                    proviceName = "阿联酋"
                }
                return proviceName;
            }

            myChart.on('click', function (params) {
                // var city = params.name;
                // loadChart(city); 
                var blockName = params.name
                blockName = changeName(blockName)
                // console.log("getResultData[blockName]['radarList']",getResultData[blockName]['radarList'])
                console.log("getResultData",getResultData)
                console.log("blockName",blockName)
                globalRadarList = getResultData[blockName]['radarList']
                minetable(globalRadarList,blockName)
                linetable(getResultData[blockName]['confirmed'], getResultData[blockName]['date'], blockName)
                console.log("globalRadarList",globalRadarList)
            });
            myChart.setOption(resultOption)
        },
    }

    )

}

