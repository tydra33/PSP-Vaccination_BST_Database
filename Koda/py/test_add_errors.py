import pexpect


def test_add_errors():
    baza = pexpect.pexpect()

    try:
        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("11111111111111")
        baza.expect(">> Invalid input data")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("11111f1111111")
        baza.expect(">> Invalid input data")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111111111")
        baza.expect("add> NAME: ")
        baza.send("4rdit")
        baza.expect(">> Invalid input data")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111111111")
        baza.expect("add> NAME: ")
        baza.send("Ardit")
        baza.expect("add> SURNAME: ")
        baza.send("N3l4")
        baza.expect(">> Invalid input data")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111111111")
        baza.expect("add> NAME: ")
        baza.send("Ardit")
        baza.expect("add> SURNAME: ")
        baza.send("Nela")
        baza.expect("add> AGE: ")
        baza.send("twenty")
        baza.expect(">> Invalid input data")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111111111")
        baza.expect("add> NAME: ")
        baza.send("Ardit")
        baza.expect("add> SURNAME: ")
        baza.send("Nela")
        baza.expect("add> AGE: ")
        baza.send("20")
        baza.expect("add> VACCINE: ")
        baza.send("SinoFarm")
        baza.expect(">> Invalid input data")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111111111")
        baza.expect("add> NAME: ")
        baza.send("Ardit")
        baza.expect("add> SURNAME: ")
        baza.send("Nela")
        baza.expect("add> AGE: ")
        baza.send("20")
        baza.expect("add> VACCINE: ")
        baza.send("Pfeizer")
        baza.expect("OK")

        print "PASSED\ttest_add_errors"

    except:
        print "FAILED\ttest_add_errors"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_add_errors()
