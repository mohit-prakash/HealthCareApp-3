$(document).ready(function(){
    //1/hide error section
    $("#specCodeError").hide();
    $("#specNameError").hide();
    $("#specNoteError").hide();

    //2. define error variable
    var specCodeError = false;
    var specNameError = false;
    var specNoteError = false;

    //3. define validate function
    function validateSpecCode(){
        var val = $("#specCode").val();
        var exp = /^[A-Z]{3,8}$/;
        if(val=="")
        {
            $("#specCodeError").show();
            $("#specCodeError").html("<b>Code</b> cannot be blank.");
            $("#specCodeError").css("color","red");
            specCodeError = false;
        }
        else if(!exp.test(val))
        {
            $("#specCodeError").show();
            $("#specCodeError").html("<b>Code</b> in uppercase and 3 to 8 chars.");
            $("#specCodeError").css("color","red");
            specCodeError = false;
        }
        else
        {
            $("#specCodeError").hide();
            specCodeError = true;
        }
        return specCodeError;
    }

    function validateSpecName()
    {
        var val = $("#specName").val();
        var exp = /^[A-Z][a-z]{2,12}$/;
        if(val=="")
        {
            $("#specNameError").show();
            $("#specNameError").html("<b>Name</b> cannot be blank.");
            $("#specNameError").css("color","red");
            specNameError = false;
        }
        else if(!exp.test(val))
        {
            $("#specNameError").show();
            $("#specNameError").html("<b>Name</b> start with caps and 3 to 13 chars");
            $("#specNameError").css("color","red");
            specNameError = false;
        }
        else
        {
            $("#specNameError").hide();
            specNameError = true;   
        }
        return specNameError;
    }

    function validateSpecNote()
    {
        var val = $("#specNote").val();
        if (val=="")
        {
            $("#specNoteError").show();
            $("#specNoteError").html("<b>Note</b> cannot be blank.");
            $("#specNoteError").css("color","red");
            specNoteError = false;   
        }
        else
        {
            $("#specNoteError").hide();
            specNoteError = true;
        }
        return specNoteError;
    }

    //action event
    $("#specCode").keyup(function() {
        validateSpecCode();
    });
    $("#specName").keyup(function() {
        validateSpecName();
    });
    $("#specNote").keyup(function() {
        validateSpecNote();
    });

    //on submit
    $("#specReg").submit(function(){
        validateSpecCode();
        validateSpecName();
        validateSpecNote();
        if(specCodeError && specNameError && specNoteError)
        {
            return true;
        }
        else
        {
            return false;
        }
    })
})