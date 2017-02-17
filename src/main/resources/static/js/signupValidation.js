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

    } else if ((username.value.length < 5) || (username.value.length > 15))
    {
        username.style.background = 'Yellow';
        errors += "Username must be at least 5 characters.\n";

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
        errors += "Please enter a valid email.\n";

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

    var illegalChars = /[\W_]/; // allow only letters and numbers

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

    } else if ((password.value.length < 7) || (password.value.length > 15))
    {
        errors += "Password must be between 7 and 15 characters.\n";
        password.style.background = 'Yellow';

        return false;

    } else if (illegalChars.test(fld.value))
    {
        errors += "The password contains illegal characters.\n";
        password.style.background = 'Yellow';

        return false;

    } else if ( (password.value.search(/[a-zA-Z]+/)==-1) || (password.value.search(/[0-9]+/)==-1) )
    {
        errors += "The password must contain at least one numeral.\n";
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