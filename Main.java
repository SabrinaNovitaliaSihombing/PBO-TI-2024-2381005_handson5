import java.util.Scanner;

public class Main {
    public static String[] todos = new String[2];
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
       showMainMenu();
    }

    public static void showTodoList() {
        System.out.println("TODO LIST");
        for (int i = 0; i < todos.length; i++) {
            String todo = todos[i];
            if (todos[i] != null) {
                System.out.println((i + 1) + ". " + todo);
            }
        }
    }

    public static void addTodoList(String todo) {
        resizeArrayIfFull();


        for (int i =  0; i <todos.length; i++) {
            if (todos[i] == null) {
                todos[i] = todo;
                break;
            }
        }
    }

    private static void resizeArrayIfFull() {
        boolean isFull = isArrayFull();

        if (isFull) {
            resizeArrayToTwoTimesBigger();

        }
    }

    private static void resizeArrayToTwoTimesBigger() {
        String[] temp = todos;
        todos = new String[todos.length * 2];
        for (int i = 0; i < temp.length; i++) {
            todos[i] = temp[i];
        }
    }

    private static boolean isArrayFull() {
        boolean isFull = true;
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public static boolean removeTodoList (Integer number) {
        if (isSelectedToDoNotValid(number))
            return false;

        for ( int i = number - 1; i < todos.length; i++) {
            // if tdo is the last element
            if ( i == (todos.length) - 1) {
                todos[i] = null;
            } else {
                // replace with the element on the right
                todos[i] = todos[i + 1];
            }
        }
        return true;
    }

    private static boolean isSelectedToDoNotValid(Integer number) {
        if (number <= 0) {
            return true;
        }

        if (number - 1 > todos.length - 1) {
            return true;
        }

        if (todos[number - 1] == null) {
            return true;
        }
        return false;
    }

    public static boolean editToDoList (Integer number, String newTodo) {
        if (isSelectedToDoNotValid(number)) {
            return false;
        }
        todos[number - 1] = newTodo;
        return true;
    }

    public static void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTodoList();
            System.out.println("Menu: ");
            System.out.println("1. Tambah");
            System.out.println("2. Hapus");
            System.out.println("3. Edit");
            System.out.println("4. Keluar");
            String selectedMenu = input("Pilih ");
            switch (selectedMenu) {
                case "1":
                    // showMenuAddToDoList();
                    break;
                case "2":
                    showMenuRemoveTodoList();
                    break;
                case "3":
                    // showMenuAddToDoList();
                    break;
                case "4":
                    isRunning = false;
                    break;
                default:
                    break;

            }
        }

    }

    public static String input(String info) {
        System.out.println(info + ": ");
        String data = scanner.nextLine();
        return data;
    }

    public static void showMenuAddTodoList() {
        System.out.println("Menambah Todo List");
        String todo = input("todo (x jika batal)");
        if (todo.equals("x")) {
            // batal
        } else {
            addTodoList(todo);
        }
    }

    public static void showMenuRemoveTodoList() {
        System.out.println("Menghapus Todo List");
        String todoYangDipilih = input("Nomor todo yang dihapus(x jika batal");
        if (todoYangDipilih.equals("x")) {
            // batal
        } else {
            boolean success = removeTodoList(Integer.valueOf(todoYangDipilih));
            if (!success) {
                System.out.println("Gagal menghapus todo list : " + todoYangDipilih);
            }
        }

    }

    public static void showMenuEditTodoList() {
        System.out.println("Mengedit Todo List");
        String selectedTodo = input("Masukkan no Todo (x jika batal");
        if (selectedTodo.equals("x")) {
            return;
        }
        String newTodo = input("Masukkan todo yang baru (x jika batal");
        if (newTodo.equals("x")) {
            return;
        }
        boolean isEditTodoSuccess = editToDoList(Integer.valueOf(selectedTodo), newTodo);
        if (isEditTodoSuccess) {
            System.out.println("Berhasil mengedit todo");
        } else {
            System.out.println("Gagal mengedit todo");
        }
    }
}