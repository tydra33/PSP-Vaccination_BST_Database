import pexpect


def test_remove_name():
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
        baza.send("Zana")
        baza.expect("add> SURNAME: ")
        baza.send("Ujani")
        baza.expect("add> AGE: ")
        baza.send("28")
        baza.expect("add> VACCINE: ")
        baza.send("AstraZeneca")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("1111111241541")
        baza.expect("add> NAME: ")
        baza.send("Kujtime Bashkimi")
        baza.expect("add> SURNAME: ")
        baza.send("Dushku")
        baza.expect("add> AGE: ")
        baza.send("31")
        baza.expect("add> VACCINE: ")
        baza.send("AstraZeneca")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("add")
        baza.expect("add> EMSO: ")
        baza.send("9121111241111")
        baza.expect("add> NAME: ")
        baza.send("Agim")
        baza.expect("add> SURNAME: ")
        baza.send("Dushku")
        baza.expect("add> AGE: ")
        baza.send("28")
        baza.expect("add> VACCINE: ")
        baza.send("AstraZeneca")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("remove")
        baza.expect("remove> NAME: ")
        baza.send("Kujtime Bashkimi")
        baza.expect("remove> SURNAME: ")
        baza.send("Dushku")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("search")
        baza.expect("search> NAME: ")
        baza.send("Kujtime Bashkimi")
        baza.expect("search> SURNAME: ")
        baza.send("Dushku")
        baza.expect(">> Person does not exist")

        print "PASSED\ttest_remove_name"

    except:
        print "FAILED\ttest_remove_name"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_remove_name()
