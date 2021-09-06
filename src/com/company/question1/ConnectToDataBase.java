    package com.company.question1;

    import com.company.Gates;
    import org.json.simple.JSONArray;
    import org.json.simple.JSONObject;
    import org.json.simple.parser.JSONParser;
    import org.json.simple.parser.ParseException;

    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.Reader;
    import java.nio.file.FileSystemAlreadyExistsException;
    import java.util.Scanner;


    public class ConnectToDataBase implements Conectable{
        public ConnectToDataBase() throws IOException, ParseException {
            connectToDataBase();
            takeStrings();
            checkString("K1");
            readStringByKey("K1");
            countOfStrings();
            updateInfoByKey("K1");
            WriteString(alls);
            countOfStrings();

        }

        private Gates[] alls;
        private boolean isConnect = false;
    @Override
    public String[] reutrnSomeString(int index, int count) {
        return new String[0];
    }

    @Override
    public void connectToDataBase() {
        isConnect = true;
    }

    @Override
    public void disconnectToDataBase() {
        isConnect = false;
    }

    @Override
    public void checkConnection() {
        System.out.println(isConnect);
    }

    @Override
    public void readStringByIndex(int index) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите индекс");
        int m = scanner.nextInt();
        print(alls[m]);
    }

    @Override
    public void checkString(String key) {
        if (isConnect) {
            for (int i = 0; i < alls.length; i++) {
                if (alls[i].getKey().equals(key)) {
                    System.out.println(String.format("С ключем %s есть запись", key));
                }
            }
        }
    }

    @Override
    public void readStringByKey(String key) {
        if (isConnect) {
            for (int i = 0; i < alls.length; i++) {
                if (alls[i].getKey().equals(key)) {
                    print(alls[i]);
                }
            }
        }

    }

    @Override
    public  void takeStrings() throws IOException, ParseException {
        if (isConnect == true) {
            Gates[] gates = readFiles("base.json");
            for (Gates gates1 : gates) {
                print(gates1);
            }
        }else if (isConnect == false) {
                System.out.println(String.format("Eror connection is %b", isConnect));
            }

    }

    @Override
    public void countOfStrings() {
        if (isConnect == true){
        System.out.println(alls.length);
        }
    }

    @Override
    public void updateStringByIndex() {
        if (isConnect == true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите индекс");
            int m = scanner.nextInt();
            System.out.println("Введите обновление значения");
            String l = scanner.nextLine();
            alls[m].updateValue(l);
        }
    }

    @Override
    public void updateInfoByKey(String key) {
        if (isConnect == true){
            Scanner scanner = new Scanner(System.in);
            String l = scanner.nextLine();
            for (int i = 0; i < alls.length; i++) {
                if (alls[i].getKey().equals(key)) {
                    alls[i].updateValue(l);
                }
            }

        }

    }


    private  Gates[] readFiles(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(fileName)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            Gates[] gates = new Gates[jsonArray.size()];
            Integer q=0;
            for (Object o : jsonArray) {
                JSONObject fact = (JSONObject) o;

                gates[q] = new Gates((String) fact.get("key"),
                        (String) fact.get("value"));
                q=q+1;
            }
            alls = gates;
            return gates;
        }
    }

        private static void print(Gates gates) {
            String frm = "%s == %s ";
            String toPrint = String.format(frm, gates.getKey(), gates.getValue());
            System.out.println(toPrint);
        }

        public void WriteString(Gates[] gates) throws IOException, ParseException {
            JSONObject[] objs = new JSONObject[gates.length];
            JSONArray ja = new JSONArray();
            for (int i = 0; i < gates.length; i++) {
                objs[i] = new JSONObject();
                objs[i].put("key", gates[i].getKey());
                objs[i].put("value", gates[i].getValue());
                ja.add(objs[i]);
                if (i == gates.length){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите ключ");
                    String j = scanner.nextLine();
                    System.out.println("Введите значение");
                    String l = scanner.nextLine();
                    objs[i] = new JSONObject();
                    objs[i].put("key", gates[i].getKey());
                    objs[i].put("value", gates[i].getValue());
                    ja.add(objs[i]);
                }
            }

            try (FileWriter file = new FileWriter("base.json")) {
                file.write(ja.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}