package src;

import java.sql.*;
import java.util.ArrayList;


public class Main {
    private static String DBconfig = "jdbc:mysql://127.0.0.1/student?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private static String DBUser = "root";
    private static String DBpassword = "";
    //数据库配置

    private static int week_nums;
    //周数

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DBconfig, DBUser, DBpassword);
        return connection;
    }//连接数据库

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        week_nums = 1;
        Connection connection = getConnection();
        //三节连上课程特殊选取
        int[] specialGroup = {5, 10, 15, 23, 26, 33};
        Statement statement = connection.createStatement();
        //从课程限制表里面取数据
        ResultSet resultSet = statement.executeQuery("SELECT * FROM class_request");
//        ArrayList<Integer> twiceList = new ArrayList<>();
//        ArrayList<Integer> threeList = new ArrayList<>();
//        while (resultSet.next()) {
//            int intervalRequire = resultSet.getInt("time");
//            int classRoomRequire = resultSet.getInt("classroom");
//            int seatsRequire = resultSet.getInt("seats");
//            int generalRequire = resultSet.getInt("general");
//            int classId = resultSet.getInt("id");
//            int maxTimesOneWeek = resultSet.getInt("times_one_week");
//
//            int classroomid = check(intervalRequire, classRoomRequire, seatsRequire, generalRequire);
//            if (classroomid <= 0) {
//                //检测同一时段是否冲突
//                continue;
//            }
//            Statement sta = connection.createStatement();
//            //初步排课,将教室和课程进行第一次比对,然后将教室分配给课程
//            if (maxTimesOneWeek == 2) {
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ")");
//                System.out.println("课程" + classId + "被排在了" + intervalRequire + "在" + classroomid + "教室");
//            } else if (maxTimesOneWeek == 3) {
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ")");
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ")");
//                System.out.println("课程" + classId + "被排在了" + intervalRequire + "在" + classroomid + "教室");
//            } else if (maxTimesOneWeek == 4) {
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ")");
//                System.out.println("课程" + classId + "被排在了" + intervalRequire + "在" + classroomid + "教室");
//                twiceList.add(classId);
//            } else if (maxTimesOneWeek == 6) {
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ")");
//                System.out.println("课程" + classId + "被排在了" + intervalRequire + "在" + classroomid + "教室");
//                twiceList.add(classId);
//                threeList.add(classId);
//            } else {
//                sta.execute("INSERT INTO time_table (`interval`,class_id,classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ");");
//            }
//            sta.close();
//        }
//        //对于课程数比较多的课程,进行二次排课
//        for (Integer temp : twiceList) {
//            resultSet = connection.createStatement().executeQuery("SELECT * FROM class_request WHERE id=" + temp);
//            while (resultSet.next()) {
//                int intervalRequire = resultSet.getInt("time");
//                int classRoomRequire = resultSet.getInt("classroom");
//                int seatsRequire = resultSet.getInt("seats");
//                int generalRequire = resultSet.getInt("general");
//                int classId = resultSet.getInt("id");
//
//                int classroomid = check(intervalRequire, classRoomRequire, seatsRequire, generalRequire);
//                if (classroomid <= 0) {
//                    //检测同一时段是否冲突
//                    continue;
//                }
//                Statement sta = connection.createStatement();
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ");");
//                System.out.println("课程" + classId + "被排在了" + intervalRequire + "在" + classroomid + "教室");
//            }
//        }
//
//        //第三次排课,针对课程数过多的课程
//        for (Integer integer : threeList) {
//            resultSet = connection.createStatement().executeQuery("SELECT * FROM class_request WHERE id=" + integer);
//            while (resultSet.next()) {
//                int intervalRequire = resultSet.getInt("time");
//                int classRoomRequire = resultSet.getInt("classroom");
//                int seatsRequire = resultSet.getInt("seats");
//                int generalRequire = resultSet.getInt("general");
//                int classId = resultSet.getInt("id");
//
//                int classroomid = check(intervalRequire, classRoomRequire, seatsRequire, generalRequire);
//                if (classroomid <= 0)
//                    //检测同一时段是否冲突
//                    continue;
//
//                Statement sta = connection.createStatement();
//                sta.execute("INSERT INTO time_table (`interval`, class_id, classroom_id) VALUES (" + intervalRequire + "," + classId + "," + classroomid + ");");
//                System.out.println("课程" + classId + "被排在了" + intervalRequire + "在" + classroomid + "教室");
//            }
//        }
//
//        //以下代码为确定课程在一天中的顺序
//        connection.createStatement().execute("UPDATE time_table,class_request SET time_table.class_propertis=class_request.general WHERE time_table.class_id=class_request.id");
//        resultSet = connection.createStatement().executeQuery("SELECT * FROM time_table WHERE `interval`=" + 1);
//        ArrayList<oneClass> firstClass = new ArrayList<>();
//        ArrayList<oneClass> secondClass = new ArrayList<>();
//        while (resultSet.next()) {
//            oneClass tempClass = new oneClass();
//            tempClass.id = resultSet.getInt("class_id");
//            tempClass.classroom = resultSet.getInt("classroom_id");
//            tempClass.timeInterval = 1;
//            int flag = 0;
//            for (int i : specialGroup)
//                if (i == tempClass.id) {
//                    flag = 1;
//                    break;
//                }
//            if (flag == 1)
//                continue;
//            if (checkAgain(resultSet.getInt("classroom_id"), firstClass)) {
//                statement.execute("UPDATE time_table SET `order`=" + 1 + " WHERE id=" + resultSet.getInt("id"));
//                firstClass.add(tempClass);
//            } else {
//                statement.execute("UPDATE time_table SET `order`=" + 2 + " WHERE id=" + resultSet.getInt("id"));
//                secondClass.add(tempClass);
//            }
//        }
//
//        resultSet = connection.createStatement().executeQuery("SELECT * FROM time_table WHERE `interval`=" + 2);
//        ArrayList<oneClass> thirdClass = new ArrayList<>();
//        ArrayList<oneClass> fourthClass = new ArrayList<>();
//        while (resultSet.next()) {
//            oneClass tempClass = new oneClass();
//            tempClass.id = resultSet.getInt("class_id");
//            tempClass.classroom = resultSet.getInt("classroom_id");
//            tempClass.timeInterval = 2;
//            int flag = 0;
//            for (int i : specialGroup)
//                if (i == tempClass.id) {
//                    flag = 1;
//                    break;
//                }
//            if (flag == 1)
//                continue;
//            if (checkAgain(resultSet.getInt("classroom_id"), firstClass)) {
//                statement.execute("UPDATE time_table SET `order`=" + 3 + " WHERE id=" + resultSet.getInt("id"));
//                firstClass.add(tempClass);
//            } else {
//                statement.execute("UPDATE time_table SET `order`=" + 4 + " WHERE id=" + resultSet.getInt("id"));
//                secondClass.add(tempClass);
//            }
//        }
//        resultSet = connection.createStatement().executeQuery("SELECT * FROM time_table WHERE `interval`=" + 0);
//        while (resultSet.next()){
//            statement.execute("UPDATE time_table SET `order`="+5+" WHERE id="+resultSet.getInt("id"));
//        }
//        //以下代码为为每一节课分配老师
//        ResultSet teacherSet = connection.createStatement().executeQuery("SELECT * FROM teacher ");
//        while (teacherSet.next()) {
//            int generalRequire = teacherSet.getInt("general");
//            int max_times_week = teacherSet.getInt("max_times_week");
//            int teacher_id = teacherSet.getInt("teacher_id");
//
//            if (teacher_id == 1 || teacher_id == 8 || teacher_id == 15 || teacher_id == 23 || teacher_id == 24 || teacher_id == 25) {
//                continue;
//            }
//            ResultSet temp = statement.executeQuery("SELECT * FROM time_table " +
//                    "WHERE " +
//                    "class_propertis=" + generalRequire +
//                    " AND time_table.teacher=0 AND `order`!='' LIMIT 0," + max_times_week/2);
//            int count = 0;
//            ArrayList<Integer> tempList = new ArrayList<>();
//            while (temp.next()) {
//                tempList.add(temp.getInt("id"));
//                count++;
//            }
//            if (count != 0) {
//                for (int id : tempList) {
//                    statement.execute("UPDATE time_table " +
//                            "SET time_table.teacher=" + teacher_id +
//                            " WHERE id=" + id);
//                }
//            }
//        }
//
//
//        //以下代码为将已经分好的课程,教室,老师的组合,通过星期数组合起来
        resultSet=statement.executeQuery("SELECT * FROM time_table ORDER BY `weekday` ASC ");
        while (resultSet.next()){
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO `time_table_ch`(weekday, `order`, class_id, classroom_id, class_properties, teacher, classroom_properties) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(resultSet.getInt("weekday")));
            int order=resultSet.getInt("order");
            String temp = null;
            switch (order){
                case 1:
                    temp="first class in morning";
                    break;
                case 2:
                    temp="second class in morning";
                    break;
                case 3:
                    temp="first class in afternoon";
                    break;
                case 4:
                    temp="second class in morning";
                    break;
                case 5:
                    temp="first class in evening";
                    break;
                case 12:
                    temp="three class first and second class in morning";
                    break;
                case 34:
                    temp="three class first and second class in afternoon";
                    break;
            }
            preparedStatement.setString(2,temp);
            preparedStatement.setInt(3,resultSet.getInt("class_id"));
            preparedStatement.setInt(4,resultSet.getInt("classroom_id"));
            preparedStatement.setInt(5,resultSet.getInt("class_propertis"));
            preparedStatement.setInt(6,resultSet.getInt("teacher"));
            switch (resultSet.getInt("classroom_properties")){
                case 1:
                    temp="mutiplayer classroom";
                    break;
                case 2:
                    temp="computer classroom";
                    break;
                case 0:
                    temp="normal classroom";
                    break;
            }
            preparedStatement.setString(7,temp);
            preparedStatement.execute();
            preparedStatement.close();
        }

        //第一题评估模型
        int timeRequireLimit = 0;
        int classroomRequireLimit = 0;
        resultSet = connection.createStatement().executeQuery("SELECT * FROM teacher");
        int maxTimeLimit=0;
        while (resultSet.next()) {
            ResultSet tempSet = connection.createStatement().executeQuery("SELECT * FROM time_table WHERE teacher=" + resultSet.getInt("teacher_id") );
            int count=0;
            while (tempSet.next()) {
                if (tempSet.getInt("classroom_properties") == resultSet.getInt("classroom_require"))
                    classroomRequireLimit++;
                if (tempSet.getInt("interval") == resultSet.getInt("time_interval_require") || resultSet.getInt("time_interval_require") == 0)
                    timeRequireLimit++;
                count++;
            }
            maxTimeLimit+=(count*2-resultSet.getInt("max_times_week"))*2/resultSet.getInt("max_times_week");
        }
        System.out.println(classroomRequireLimit);
        System.out.println(timeRequireLimit);
        System.out.println(maxTimeLimit);

        //第二题评估模型
//        int timeRequireLimit = 0;
//        int classroomRequireLimit = 0;
//        resultSet = connection.createStatement().executeQuery("SELECT * FROM teacher");
//        int maxTimeLimit=0;
//        while (resultSet.next()) {
//            ResultSet tempSet = connection.createStatement().executeQuery("SELECT * FROM time_table_copy WHERE teacher=" + resultSet.getInt("teacher_id") );
//            int count=0;
//            while (tempSet.next()) {
//                if (tempSet.getInt("classroom_properties") == resultSet.getInt("classroom_require"))
//                    classroomRequireLimit++;
//                if (tempSet.getInt("interval") == resultSet.getInt("time_interval_require") || resultSet.getInt("time_interval_require") == 0)
//                    timeRequireLimit++;
//                count++;
//            }
//            maxTimeLimit+=(count*2-resultSet.getInt("max_times_week"))*2/resultSet.getInt("max_times_week");
//        }
//        System.out.println(classroomRequireLimit);
//        System.out.println(timeRequireLimit);
//        System.out.println(maxTimeLimit);


        statement.close();
        connection.close();
    }

    //该函数为确定该课程上课位置
    private static int check(int ir, int cr, int sr, int gr) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `classroom_request` WHERE `max_seats`>=" + sr + " AND `property`=" + cr + " ORDER BY max_seats ASC ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(resultSet.getInt("id"));
        }
        int timeInterval = 0;
        for (Integer integer : arrayList) {
            if (ir == 1)
                timeInterval = 1;
            else if (ir == 2)
                timeInterval = 2;
            ResultSet temp = statement.executeQuery("SELECT `id` FROM `time_table` WHERE `classroom_id`=" + integer + " AND `interval`=" + timeInterval + " ORDER BY `id` ASC ");
            ArrayList<Integer> tempList = new ArrayList<>();
            int rowCount = 0;
            while (temp.next()) {
                rowCount++;
                tempList.add(temp.getInt("id"));
            }
            if (rowCount >= 10)
                return 0;
            if (tempList.contains(integer))
                continue;
            else
                return integer;
        }
        return 0;
    }


    //该函数为确定同一时段是否有相同课程
    private static boolean checkAgain(int classroom, ArrayList<oneClass> arrayList) {
        int result = 0;
        for (oneClass temp : arrayList) {
            if (classroom == temp.classroom)
                result++;
        }
        if (result >= 5)
            return false;
        return true;
    }


    static class oneClass {
        public int id;
        public int classroom;
        public int timeInterval;
    }


}