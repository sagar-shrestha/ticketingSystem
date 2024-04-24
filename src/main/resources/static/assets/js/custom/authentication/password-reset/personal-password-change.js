// Define form element
const form = document.getElementById('kt_personal_password_change_form');

// Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
var validator = FormValidation.formValidation(
    form,
    {
        fields: {
			'oldPassword': {
                validators: {
                    notEmpty: {
                        message: 'Old Password is required'
                    }
                }
            },

            'newPassword': {
                validators: {
                    notEmpty: {
                        message: 'Password is required'
                    },regexp: {
                                    regexp: /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/i,
                                    message: 'Please enter valid password',
                                }
                }
            },
            'confirmPassword': {
                validators: {

				identical: {
                                    compare: function () {
                                        return form.querySelector('[name="newPassword"]').value;
                                    },
                                    message: 'The new password and its confirm are not the same',
                                },
                }
            },
        },

        plugins: {
            trigger: new FormValidation.plugins.Trigger(),
            bootstrap: new FormValidation.plugins.Bootstrap5({
                rowSelector: '.fv-row',
                eleInvalidClass: '',
                eleValidClass: ''
            })
        }

    }
);

// Revalidate the confirmation password when changing the password
                form.querySelector('[name="newPassword"]').addEventListener('input', function () {
                    validator.revalidateField('confirmPassword');
                });
// Submit button handler
const submitButton = document.getElementById('kt_personal_password_change_submit');
submitButton.addEventListener('click', function (e) {
    // Prevent default button action
    e.preventDefault();

    // Validate form before submit
    if (validator) {
        validator.validate().then(function (status) {
            console.log('validated!');

            if (status == 'Valid') {
                // Show loading indication
                submitButton.setAttribute('data-kt-indicator', 'on');

                // Disable button to avoid multiple click
                submitButton.disabled = true;

                // Simulate form submission. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                setTimeout(function () {
                    // Remove loading indication
                    submitButton.removeAttribute('data-kt-indicator');

                    // Enable button
                    submitButton.disabled = false;

                    // Show popup confirmation
                     Swal.fire({
                        text: "Form has been successfully submitted!",
                        icon: "success",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary"
                        }
                    }); 

                    form.submit(); // Submit form
                }, 2000);
            }
        });
    }
});
 