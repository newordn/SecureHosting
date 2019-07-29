

paypal.Buttons({
    createOrder: function(data, actions) {
        return actions.order.create({
            purchase_units: [{
                amount: {
                    value: $("#amount").val()
                }
            }],
            description: 'Secure Hosting Payment, Ssl Certificates'
        });
    },
    onApprove: function(data, actions) {
        return actions.order.capture().then(function(details) {
            alert('Transaction completed with success');
            $("#orderId").val(data.orderId)
            $("#amount2").val($("#amount").val())

// Call your server to save the transaction
            document.getElementById("paymentSuccessForm").click()

        });
    }
}).render('#paypal-button-container');