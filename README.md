# android sqlite 操作
### 1.继承SQLiteOpenHelper，并重写相关函数
  * 必须重写构造函数
  * 重写onCreate(SQLiteDatabase db)可在内部创建数据库表
  * 重写onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)数据库版本更新时调用
 
### 2.调用
    //获取数据库
    PersonDBHelper dbHelper = 
    new PersonDBHelper(MainActivity.this, "person_db", null, 1);
    SQLiteDatabase db = dbHelper.getWritableDatabase();