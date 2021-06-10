import pexpect


def test_add_duplicate():
    baza = pexpect.pexpect()

    try:
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
        baza.send("Moderna")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111111111")
        baza.expect("add> NAME: ")
        baza.send("Gjergj")
        baza.expect("add> SURNAME: ")
        baza.send("Dushku")
        baza.expect("add> AGE: ")
        baza.send("28")
        baza.expect("add> VACCINE: ")
        baza.send("AstraZeneca")
        baza.expect(">> Person already exists")

        baza.expect("command> ")
        baza.send("count")
        baza.expect("1")

        baza.expect("command> ")
        baza.send("print")
        baza.expect("Database size: 1")
        baza.expect("1111111111111-Ardit, Nela-20-Moderna")
        baza.expect("OK")

        print "PASSED\ttest_add_duplicate"

    except:
        print "FAILED\ttest_add_duplicate"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_add_duplicate()
