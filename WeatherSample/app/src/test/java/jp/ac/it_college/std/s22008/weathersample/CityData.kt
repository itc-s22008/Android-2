package jp.ac.it_college.std.s22008.weathersample

class CityData {
    data class City(
        val name: String,
        val q: String
    )
    val cityList = listOf(
    City("北海道", "札幌"),
    City("青森", "null"),
    City("岩手", "盛岡"),
    City("宮城", "仙台"),
    City("秋田", "null"),
    City("山形", "null"),
    City("福島", "null"),
    City("茨城", "水戸"),
    City("栃木", "宇都宮"),
    City("群馬", "前橋"),
    City("埼玉", "さいたま"),
    City("千葉", "null"),
    City("東京","null"),
    City("神奈川","横浜"),
    City("新潟", "null"),
    City("富山", "null"),
        City("石川", "金沢"),
        City("福井", "null"),
        City("山梨", "甲府"),
        City("長野", "null"),
        City("岐阜", "null"),
        City("静岡", "null"),
        City("愛知", "名古屋"),
        City("三重", "津"),
        City("滋賀", "大津"),
        City("京都", "null"),
        City("大阪", "null"),
        City("兵庫", "null"),
        City("奈良", "null"),
        City("和歌山", "null"),
        City("鳥取", "null"),
        City("島根", "松江"),
        City("岡山", "null"),
        City("広島", "null"),
        City("山口", "null"),
        City("徳島", "null"),
        City("香川", "高松"),
        City("愛媛", "松山"),
        City("高知", "null"),
        City("福岡", "null"),
        City("佐賀", "null"),
        City("長崎", "null"),
        City("熊本", "null"),
        City("大分", "null"),
        City("宮崎", "null"),
        City("鹿児島", "null"),
        City("沖縄", "那覇"),
        )

    companion object {
        val size: Int
            get() {
                TODO()
            }
    }
}