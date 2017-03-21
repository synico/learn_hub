package com.comparator;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TimeComparator {
	
	private DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.S");
	
	private static List<TimeObj> lists = new ArrayList(3);
	
	class TimeObj {
		
		private Timestamp lastCreate;
		
		private Timestamp lastUpdate;
		
		public Timestamp getLastCreate() {
			return lastCreate;
		}
		public void setLastCreate(Timestamp lastCreate) {
			this.lastCreate = lastCreate;
		}
		public Timestamp getLastUpdate() {
			return lastUpdate;
		}
		public void setLastUpdate(Timestamp lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
		
		public String toString() {
			return this.lastCreate + "|" + this.lastUpdate;
		}
	}
	
	TimeObj getTimeObj() {
		return new TimeObj();
	}
	
	class MyTimeComparator<T> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			TimeObj to1 = (TimeObj)o1;
			TimeObj to2 = (TimeObj)o2;
			if(to1.getLastCreate().after(to2.getLastCreate())) {
				return 1;
			}
			return 0;
		}

	}
	
	MyTimeComparator<TimeObj> getComparator() {
		return new MyTimeComparator<TimeObj>();
	}
	
	private void initData() {
		try {
			TimeObj tObj1 = getTimeObj();
			Date cdate1 = dateFormat.parse("16-MAR-17 10.08.14.401");
			Timestamp ts11 = new Timestamp(cdate1.getTime());
			tObj1.setLastCreate(ts11);
			Date udate1 = dateFormat.parse("16-MAR-17 10.11.08.834");
			Timestamp ts12 = new Timestamp(udate1.getTime());
			tObj1.setLastUpdate(ts12);
			lists.add(tObj1);
			
			TimeObj tObj0 = getTimeObj();
			Date cdate0 = dateFormat.parse("16-MAR-17 10.11.02.874");
			Timestamp ts01 = new Timestamp(cdate0.getTime());
			tObj0.setLastCreate(ts01);
			Date udate0 = dateFormat.parse("16-MAR-17 10.11.08.846");
			Timestamp ts02 = new Timestamp(udate0.getTime());
			tObj0.setLastUpdate(ts02);
			lists.add(tObj0);
			
			TimeObj tObj2 = getTimeObj();
			Date cdate2 = dateFormat.parse("06-MAR-17 06.04.30.713");
			Timestamp ts21 = new Timestamp(cdate2.getTime());
			tObj2.setLastCreate(ts21);
			Date udate2 = dateFormat.parse("16-MAR-17 10.11.08.846");
			Timestamp ts22 = new Timestamp(udate2.getTime());
			tObj2.setLastUpdate(ts22);
			lists.add(tObj2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
//		new TimeComparator().initData();
//		for(TimeObj obj : lists) {
//			System.out.println(obj);
//		}
//		Collections.sort(lists, new TimeComparator().getComparator());
//		System.out.println("after sorted");
//		for(TimeObj obj : lists) {
//			System.out.println(obj);
//		}
		Queue<String> myQ = new PriorityQueue<String>();
		myQ.add("2");
		myQ.add("1");
		myQ.add("3");
		
		for(;!myQ.isEmpty();) {
			System.out.println(myQ.poll());
		}
		
		
	}

}
