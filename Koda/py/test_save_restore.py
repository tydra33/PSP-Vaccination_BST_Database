import pexpect


def test_save_restore():
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
        baza.send("save test.bin")
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

        baza.expect("command> ")
        baza.send("restore test.bin")
        baza.expect("OK")

        baza.expect("command> ")
        baza.send("print")
        baza.expect("Database size: 1")
        baza.expect("1111111241111-Zana, Ujani-28-AstraZeneca")
        baza.expect("OK")

        print "PASSED\ttest_save_restore"

    except:
        print "FAILED\ttest_save_restore"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_save_restore()
