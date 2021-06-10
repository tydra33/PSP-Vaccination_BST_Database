import pexpect


def test_search():
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
        baza.send("search 1111111241111")
        baza.expect("1111111241111-Zana, Ujani-28-AstraZeneca")

        baza.expect("command> ")
        baza.send("search")
        baza.expect("search> NAME: ")
        baza.send("Zana")
        baza.expect("search> SURNAME: ")
        baza.send("Ujani")
        baza.expect("1111111241111-Zana, Ujani-28-AstraZeneca")

        print "PASSED\ttest_search"

    except:
        print "FAILED\ttest_search"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_search()
