class Settings():
    def __init__(self):
        """初始化游戏设置"""
        #屏幕设置
        self.screen_width = 1200
        self.screen_height = 800
        self.bg_color = (230,230,230)

        #飞船设置
        self.ship_speed_factor = 1.5
        self.ship_limit = 3

        #子弹设置
        self.bullet_speed_factor = 1
        self.bullet_width = 1000
        self.bullet_height = 15
        self.bullet_color = 60,60,60
        self.bullets_allowed = 5

        #外星人设置
        self.alien_speed_factor = 1
        self.fleet_drop_speed = 1
            #fleet_dirction为1表示右移，为-1表示左移
        self.fleet_direction = 1
