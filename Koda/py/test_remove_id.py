import pexpect


def test_remove():
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
        baza.send("Kujtime Bashkime")
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
        baza.send("remove 1111111111111")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("search 1111111111111")
        baza.expect(">> Person does not exist")

        print "PASSED\ttest_remove_id"
        
    except:
        print "FAILED\ttest_remove_id"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_remove()
