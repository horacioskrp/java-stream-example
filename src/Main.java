import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<User> userList = getUsers();
        System.out.println("___Welcome___");

        //map
        List<String> empname = userList.stream()
                .map(User::getName).toList();
        //System.out.println(empname);


        //year budget
        double budget = userList.stream()
                .mapToDouble(User::getSalary).sum();

        //System.out.println(budget);

        //filter
        List<User> filteredList = userList.stream()
                .filter( user -> user.getAge() >20)
                .toList();

        List<User> filteredList2 = userList.stream()
                        .filter(user -> user.getGender().equals(Gender.FEMALE))
                                .toList();
        //filteredList2.forEach(System.out::println);

        //sort
        List<User> sortedList = userList.stream()
                .sorted(Comparator.comparing(User::getAge)
                        .thenComparing(User::getGender)).toList();

        //sortedList.forEach(System.out::println);

        //All match
        boolean allMatchedList = userList.stream()
                .allMatch(user -> user.getAge() > 5);

        //any match
        boolean anyMatchedList = userList.stream()
                        .anyMatch(user -> user.getAge() > 20);

        //none Match
        boolean noneMatcheList = userList.stream()
                        .noneMatch(user -> user.getAge() > 300);
       // System.out.println(noneMatcheList);

        // max
        userList.stream().max(Comparator.comparing(User::getAge))
                .ifPresent(System.out::println);

        System.out.println("----------------------");
        /*userList.stream().min(Comparator.comparing(User::getAge))
                .ifPresent(System.out::println);*/

        //Group
        Map<Gender, List<User>> userGroupByGender = userList.stream()
                .collect(Collectors.groupingBy(User::getGender));
        //System.out.println(userGroupByGender);

        //Group by sex and total year paid
        Map<Gender, Double> salaryGroupByPaid = userList.stream()
                .collect(Collectors.groupingBy(User::getGender, Collectors.summingDouble(User::getSalary)));
        System.out.println(salaryGroupByPaid);
    }

    private static List<User> getUsers() {
        return List.of(
                new User("YoroIchi Kendji", 20, Gender.MALE, 100_000),
                new User("Bernadette", 23, Gender.FEMALE, 70_000),
                new User("Noriko Nar", 57, Gender.FEMALE,50_000),
                new User("Senbomzarkura Kageyochi", 14, Gender.MALE,30_000),
                new User("Roronoa Zoro", 99, Gender.MALE,1_000_000),
                new User("Lima Alimatou", 7, Gender.FEMALE, 3_000_000),
                new User("Zelda Brown", 120, Gender.FEMALE,10_000),
                new User("Yamamoto Genriyousi", 200, Gender.MALE, 80_000),
                new User("Zaraki Kenpachi", 120, Gender.MALE, 20_000),
                new User("operi Brown", 19, Gender.MALE, 30_000)

        );
    }

}