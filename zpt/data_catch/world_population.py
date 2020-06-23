import json
from country_codes import get_country_code
import pygal
import pygal_maps_world.maps
from pygal.style import RotateStyle

#将数据加载到列表中
filename = "population_data.json"
pop_data = []
with open(filename) as f:
    pop_data = json.load(f)


cc_populations = {}
#打印2010年的人口
for pop_dict in pop_data:
    if pop_dict['Year'] == 2010:
        country_name = pop_dict['Country Name']
        population = int(pop_dict['Value'])
        code = get_country_code(country_name)
        if code:
            # print(code + ": " + str(population))
            cc_populations[code] = population
        # else:
        #     print("Error - " + country_name)
    
cc_pops_1,cc_pops_2,cc_pops_3 = {},{},{}
for cc,pop in cc_populations.items():
    if pop < 10000000:
        cc_pops_1[cc] = pop
    elif pop < 1000000000: 
        cc_pops_2[cc] = pop
    else:
        cc_pops_3[cc] = pop

wm_style = RotateStyle('#336699')
wm = pygal_maps_world.maps.World(style=wm_style)
wm.title = "World Population in 2010 by Country"


# wm.add('2010',cc_populations)
wm.add('0-10M',cc_pops_1)
wm.add('10M-1BN',cc_pops_2)
wm.add('>1BN',cc_pops_3)
wm.render_to_file('world_population_group.svg')