import pexpect


def test_exit():
    baza = pexpect.pexpect()

    try:
        baza.expect("command> ")
        baza.send("exit")
        baza.expect(">> Bye")

        print "PASSED\ttest_exit"

    except:
        print "FAILED\ttest_exit"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_exit()
