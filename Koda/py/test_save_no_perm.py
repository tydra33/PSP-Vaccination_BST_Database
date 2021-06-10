import os, stat
import pexpect


def test_save_no_perm():
    baza = pexpect.pexpect()
    filename = "test.bin"

    try:
        fd = open(filename, "w")
        fd.close()

        mode = os.stat(filename)[stat.ST_MODE]
        os.chmod(filename, mode & ~stat.S_IWRITE)

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
        baza.expect(">> IO error test.bin (Access is denied)")
        baza.expect("command> ")

        print "PASSED\ttest_save_no_perm"

    except:
        print "FAILED\ttest_save_no_perm"

    finally:
        baza.kill()
        os.chmod(filename, stat.S_IWRITE)
        os.remove(filename)


if __name__ == "__main__":
    test_save_no_perm()

