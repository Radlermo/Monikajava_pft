package pl.stqa.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



public class GroupDataGenerator {

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();//tworzymy nowy obiekt w bieżącej grupie
        JCommander jCommander = new JCommander(generator);//tworzymy obiekt typu Jcommander, pierwszy objekt to obiekt który powinien mieć wypełnione atrybuty (@parameter),
// drugi parametr to ten który jest przekazywany w wierszu poleceń
         // int count = Integer.parseInt(args[0]); //ilość grup
       // File file = new File(args[1]); //ścieżka do pliku
        //może się wygenerować wyjątek więc stosujemy metodę try..catch//wywołujemy metodę parse i przekazujemy argumenty
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
        jCommander.usage();    //wyświetlamy wyjatek na consoli
            return;
        }
        generator.run();//uruchamiamy generator



    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);//wygenerowanie danych
        save(groups, new File(file));     //zapisanie danych w pliku
         }

    private void save(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file); //otwieramy plik do zapisu
        for (GroupData group : groups) {//przechodzimy w pętli po wszytskich grupach które znajdują się na liście groups
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));//zapisanie pliku
        }
        writer.close();
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i)).withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
