import csv
from matplotlib import pyplot as plt
from datetime import datetime

filename = 'death_valley_2014.csv'
with open(filename) as f:
    reader = csv.reader(f)
    header_row = next(reader)
    # print(header_row)
    dates,highs,lows = [],[],[]
    for row in reader:
        try:
            high = int(row[1])
            current_date = datetime.strptime(row[0],"%Y-%m-%d")
            low = int(row[3])
        except ValueError:
            print(current_date,' -> missing data')
        else:
            highs.append(high)
            dates.append(current_date)
            lows.append(low)

#绘制图形
fig = plt.figure(dpi=128,figsize=(10,6))
plt.plot(dates,highs,c='red')
plt.plot(dates,lows,c='blue')

plt.fill_between(dates,highs,lows,facecolor='blue',alpha=0.1)

#设置图形格式
plt.title("Daily high and low temperatures, 2014",fontsize=24)
plt.xlabel('',fontsize=16)
fig.autofmt_xdate()
plt.ylabel("Temperatre (F)",fontsize=16)
plt.tick_params(axis='both',which='major',labelsize=16)

plt.show()
