package pl.stqa.pft.addressbook.Generators;

import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



public class GroupDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]); //ilość grup
        File file = new File(args[1]); //ścieżka do pliku


        List<GroupData> groups = generateGroups(count);//wygenerowanie danych
        save(groups, file);     //zapisanie danych w pliku
    }

    private static void save(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file); //otwieramy plik do zapisu
        for (GroupData group : groups) {//przechodzimy w pętli po wszytskich grupach które znajdują się na liście groups
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));//zapisanie pliku
        }
        writer.close();
    }

    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i)).withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}

