import pexpect


def test_reset():
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
        baza.send("1111111241111")
        baza.expect("add> NAME: ")
        baza.send("Gjergj")
        baza.expect("add> SURNAME: ")
        baza.send("Dushku")
        baza.expect("add> AGE: ")
        baza.send("28")
        baza.expect("add> VACCINE: ")
        baza.send("AstraZeneca")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("reset")
        baza.expect("reset> Are you sure (y|n): ")
        baza.send("n")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("print")
        baza.expect("Database size: 2")
        baza.expect("1111111111111-Ardit, Nela-20-Moderna")
        baza.expect("1111111241111-Gjergj, Dushku-28-AstraZeneca")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("reset")
        baza.expect("reset> Are you sure (y|n): ")
        baza.send("y")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("print")
        baza.expect("Database size: 0")
        baza.expect("OK")

        print "PASSED\ttest_reset"

    except:
        print "FAILED\ttest_reset"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_reset()
