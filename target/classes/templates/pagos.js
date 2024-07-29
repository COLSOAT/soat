const renderPaymentBrick = async (bricksBuilder) => {
    const settings = {
        initialization: {
            amount: 10000,
            preferenceId: "<PREFERENCE_ID>", // Reemplaza <PREFERENCE_ID> con el ID real de la preferencia
            payer: {
                firstName: "Oscar",
                lastName: "Carrillo",
                email: "carrillozuletaoscar@gmail.com",
            },
        },
        customization: {
            visual: {
                style: {
                    theme: "default",
                },
            },
            paymentMethods: {
                creditCard: "all",
                debitCard: "all",
                ticket: "all",
                bankTransfer: "all",
                atm: "all",
                onboarding_credits: "all",
                maxInstallments: 1
            },
        },
        callbacks: {
            onReady: () => {
                console.log("Brick está listo.");
            },
            onSubmit: ({ selectedPaymentMethod, formData }) => {
                console.log(formData)
                return new Promise((resolve, reject) => {
                    fetch("http://localhost:8080/app/pse/process_payment", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                            ...formData,
                            selectedPaymentMethod
                        }),

                    })
                        .then((response) => response.json())
                        .then((response) => {
                            console.log("Resultado del pago:", response);
                            resolve();
                        })
                        .catch((error) => {
                            console.error("Error al crear el pago:", error);
                            reject();
                        });
                });
            },
            onError: (error) => {
                console.error("Error en Brick:", error);
            },
        },
    };

    window.paymentBrickController = await bricksBuilder.create(
        "payment",
        "paymentBrick_container",
        settings
    );
};

document.addEventListener('DOMContentLoaded', () => {
    const mp = new MercadoPago('APP_USR-19238996-ec4e-48e6-853a-e6c84738be53', {
        locale: 'es'
    });
    const bricksBuilder = mp.bricks();

    const payButton = document.getElementById('payButton');
    payButton.addEventListener('click', () => {
        renderPaymentBrick(bricksBuilder);
    });
});
