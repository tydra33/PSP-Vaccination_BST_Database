import pexpect


def test_search_illegal():
    baza = pexpect.pexpect()

    try:
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
        baza.send("search 2111111241111")
        baza.expect(">> Person does not exist")

        baza.expect("command> ")
        baza.send("search")
        baza.expect("search> NAME: ")
        baza.send("Zana Kushtrimi")
        baza.expect("search> SURNAME: ")
        baza.send("Ujani")
        baza.expect(">> Person does not exist")

        print "PASSED\ttest_search_illegal"

    except:
        print "FAILED\ttest_search_illegal"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_search_illegal()
