import pexpect


def test_remove_illegal():
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
        baza.send("remove 2111111111111")
        baza.expect(">> Person does not exist")

        baza.expect("command> ")
        baza.send("remove")
        baza.expect("remove> NAME: ")
        baza.send("Kujtime Bashkimi")
        baza.expect("remove> SURNAME: ")
        baza.send("Dushku")
        baza.expect(">> Person does not exist")

        print "PASSED\ttest_remove_illegal"

    except:
        print "FAILED\ttest_remove_illegal"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_remove_illegal()
