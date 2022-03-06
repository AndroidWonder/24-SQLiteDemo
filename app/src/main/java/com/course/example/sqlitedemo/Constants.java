package com.course.example.sqlitedemo;

public class Constants {

	public static final String DATABASE_NAME = "zoo.db";
	public static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "animals";
	public static final String KEY_NAME = "name";
	public static final String KEY_Q = "Quantity";
	public static final String KEY_ID = "id integer primary key autoincrement";
	public static final String CREATE_TABLE = "CREATE TABLE animals ("
			+ Constants.KEY_ID + "," + Constants.KEY_NAME + " text,"
			+ Constants.KEY_Q + " integer);";



}
