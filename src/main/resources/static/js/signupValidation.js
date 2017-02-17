/*<![CDATA[*/

var errors = "";

function validateForm()
{
    if (!validateUsername() || !validateEmail() || !validatePassword())
    {
        alert(errors);
        errors = "";

        return false;
    }
}

function validateUsername()
{
    var username = document.getElementById("username");
    var illegalChars = /\W/; // allow letters, numbers, and underscores

    if (username.value == "")
    {
        username.style.background = 'Yellow';
        errors += "Username cannot be empty.\n";

        return false;

    } else if ((username.value.length < 3) || (username.value.length > 25))
    {
        username.style.background = 'Yellow';
        errors += "Username must be between 3 and 25 characters in length.\n";

        return false;

    } else if (illegalChars.test(username.value))
    {
        username.style.background = 'Yellow';
        errors += "Username contains illegal characters.\n";

        return false;

    }
    else
    {
        username.style.background = 'White';

        return true;
    }
}

function validateEmail()
{
    var email = document.getElementById("emailaddress");
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(email.value))
    {
        email.style.background = 'Yellow';
        errors += "Email is invalid.\n";

        return false;
    }
    else
    {
        email.style.background = 'White';

        return true;
    }
}

function validatePassword()
{
    var password = document.getElementById("password");
    var passwordConfirmation = document.getElementById("passwordConfirmation");

    // only numbers, letters, and underscores
    var illegalChars = /[\W]/;

    if (password.value != passwordConfirmation.value)
    {
        password.style.background = 'Yellow';
        passwordConfirmation.style.background = 'Yellow';
        errors += "Passwords do not match.\n";

        return false;
    } else if (password.value == "")
    {
        password.style.background = 'Yellow';
        errors = "Password cannot be empty.\n";

        return false;

    } else if ((password.value.length < 8) || (password.value.length > 128))
    {
        errors += "Password must be between 8 and 128 characters in length.\n";
        password.style.background = 'Yellow';

        return false;

    } else if (illegalChars.test(password.value))
    {
        errors += "The password contains illegal characters.\n";
        password.style.background = 'Yellow';

        return false;

    } else
    {
        password.style.background = 'White';
        passwordConfirmation.style.background = 'White';

        return true;
    }
}

/*]]>*/