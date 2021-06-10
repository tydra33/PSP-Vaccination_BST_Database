import java.io.*;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SeznamiUV {

    Bst<Person> seznamPoImenu = new Bst<>(new PersonByName());
    Bst<Person> seznamPoEmso = new Bst<>(new PersonById());

    private int count = -1;
    private int countRem = -1;
    private int countFind = -1;
    private boolean reset = false;
    String id = null;
    String name = null;
    String surname = null;
    String age = null;
    String vaccine = null;
    String remName = null;
    String remSurname = null;
    String findName = null;
    String findSurname = null;

    static String memoryError = ">> Not enough memory, operation failed";

    public String processInput(String input) {
        String token;
        String result = "OK";
        String[] params = input.split(" ");

        if (params.length == 0) {
            return ">> Enter command";
        }
        else {
            token = params[0];
        }

        if (token.equals("exit")) {
            return ">> Bye";
        }
        try {
            if (token.equals("add") || count > -1) {
                result = "add> ";
                if (token.equals("add") && params.length == 1 && count == -1) {
                    count++;
                    result += "EMSO: ";
                } else if (count > -1 && !token.equals("add")) {
                    switch (count) {
                        case 0:
                            count++;
                            result += "NAME: ";

                            id = params[0];
                            if (!id.matches("-?\\d+") || id.length() != 13) {
                                count = -1;
                                throw new UnsupportedOperationException();
                            }

                            break;
                        case 1:
                            count++;
                            result += "SURNAME: ";

                            if (params.length == 2) {
                                name = params[0] + " " + params[1];
                            } else if (params.length == 1) {
                                name = params[0];
                            }

                            if (! name.matches("^[ a-zA-Z]+$")) {
                                count = -1;
                                throw new UnsupportedOperationException();
                            }

                            break;
                        case 2:
                            count++;
                            result += "AGE: ";

                            surname = params[0];
                            if (! surname.matches("[a-zA-Z]+")) {
                                count = -1;
                                throw new UnsupportedOperationException();
                            }

                            break;
                        case 3:
                            count++;
                            result += "VACCINE: ";

                            age = params[0];
                            if (!age.matches("-?\\d+")) {
                                count = -1;
                                throw new UnsupportedOperationException();
                            }

                            break;
                        case 4:
                            count++;
                            result = "OK";

                            vaccine = params[0];
                            if (!(vaccine.equals("Pfeizer") || vaccine.equals("Moderna") || vaccine.equals("AstraZeneca") ||
                                    vaccine.equals("Johnson"))) {
                                count = -1;
                                throw new UnsupportedOperationException();
                            }

                            if (seznamPoEmso.exists(new Person(id, "", "", "", "")) ||
                            seznamPoImenu.exists(new Person("", name, surname, "", ""))) {
                                count = -1;
                                throw new IllegalArgumentException();
                            }

                            Person person = new Person(id, name, surname, age, vaccine);
                            seznamPoImenu.add(person);
                            seznamPoEmso.add(person);

                            count = -1;
                            break;
                    }
                }
            }
            else if (token.equals("remove") || countRem > -1) {
                if (token.equals("remove") && params.length == 1 && countRem == -1) {
                    countRem++;
                    result = "remove> NAME: ";
                } else if (countRem > -1 && !token.equals("remove")) {
                    if (countRem == 0) {
                        countRem++;

                        if (params.length == 2) {
                            remName = params[0] + " " + params[1];
                        } else if (params.length == 1) {
                            remName = params[0];
                        }

                        result = "remove> SURNAME: ";
                    } else if (countRem == 1) {
                        countRem = -1;
                        remSurname = params[0];

                        if (!seznamPoImenu.exists(new Person("", remName, remSurname, "", "")))
                            throw new NoSuchElementException();

                        String id = seznamPoImenu.search(new Person("", remName,
                                remSurname, "", "")).getId();
                        seznamPoImenu.remove(new Person("", remName, remSurname, "", ""));
                        seznamPoEmso.remove(new Person(id, "", "", "", ""));

                        result = "OK";
                    }
                } else if (params.length == 2) {
                    if (!seznamPoEmso.exists(new Person(params[1], "", "", "", "")))
                        throw new NoSuchElementException();

                    Person person = seznamPoEmso.search(new Person(params[1], "", "", "", ""));
                    seznamPoEmso.remove(new Person(params[1], "", "", "", ""));
                    seznamPoImenu.remove(new Person("", person.getName(), person.getSurname(), "", ""));
                }
                else
                    result = ">> Specify at least two strings";
            }
            else if (token.equals("count")){
                result = seznamPoImenu.size() + "";
            }
            else if (!reset && token.equals("reset")) {
                reset = true;
                result = ("reset> Are you sure (y|n): ");
            }
            else if (reset && !token.equals("reset")) {
                if (params[0].equals("y")) {
                    while (!seznamPoImenu.isEmpty()) {
                        seznamPoImenu.removeFirst();
                    }
                    while (!seznamPoEmso.isEmpty()) {
                        seznamPoEmso.removeFirst();
                    }
                }

                reset = false;
            }
            else if (token.equals("exists")){
                result = "No";
                if (params.length == 3)  {
                    if (seznamPoImenu.exists(new Person("", params[1], params[2], "", "")))
                        result = "Yes";
                } else if (params.length == 2)  {
                    if (seznamPoEmso.exists(new Person(params[1], "", "", "", "")))
                        result = "Yes";
                }
                else {
                    result = ">> Specify at least two strings";
                }
            }
            else if (token.equals("print")){
                seznamPoImenu.print();
            }
            else if (token.equals("save")){
                if (params.length == 2) {
                    seznamPoImenu.save(new FileOutputStream(params[1]));
                } else {
                    result = ">> Specify a file name";
                }
            }
            else if (token.equals("restore")){
                if (params.length == 2) {
                    seznamPoImenu.restore(new FileInputStream(params[1]));
                } else {
                    result = ">> Specify a file name";
                }
            }
            else if (token.equals("search") || countFind > -1) {
                if (token.equals("search") && params.length == 1 && countFind == -1) {
                    countFind++;
                    result = "search> NAME: ";
                } else if (countFind > -1 && !token.equals("search")) {
                    if (countFind == 0) {
                        countFind++;

                        if (params.length == 2) {
                            findName = params[0] + " " + params[1];
                        } else if (params.length == 1) {
                            findName = params[0];
                        }

                        result = "search> SURNAME: ";
                    } else if (countFind == 1) {
                        countFind = -1;
                        findSurname = params[0];

                        if (!seznamPoImenu.exists(new Person("", findName, findSurname, "", ""))) {
                            throw new NoSuchElementException();
                        }

                        result = "" + seznamPoImenu.search(new Person("", findName, findSurname, "", ""));
                    }
                } else if (params.length == 2)  {
                    if (!seznamPoEmso.exists(new Person(params[1], "", "", "", ""))) {
                        throw new NoSuchElementException();
                    }

                    result = "" + seznamPoEmso.search(new Person(params[1], "", "", "", ""));
                }
                else
                    result = ">> Specify at most two strings";
            }
            else {
                result = ">> Invalid command";
            }


        } catch (UnsupportedOperationException e) {
            result = ">> Invalid input data";
        } catch (IllegalArgumentException e) {
            result = ">> Person already exists";
        } catch (java.util.NoSuchElementException e) {
            result = ">> Person does not exist";
        } catch (IOException e) {
            result = ">> IO error " + e.getMessage();
        } catch (ClassNotFoundException e) {
            result = ">> Unknown format";
        } catch (OutOfMemoryError e) {
            return memoryError;
        }

        return result;
    }

    public int getCount() {
        return count;
    }

    public boolean isReset() {
        return reset;
    }

    public int getCountFind() {
        return countFind;
    }

    public int getCountRem() {
        return countRem;
    }
}
