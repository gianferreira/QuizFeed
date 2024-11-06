package com.example.quizfeed

class QuestionObject(
    var description: String,
    var complement: String,
    var questionImage: String,
    var firstOption: String,
    var secondOption: String,
    var thirdOption: String,
    var fourthOption: String,
    var correctOptionIndex: Int,
)

object QuizQuestions {
    private val questions = arrayOf(
        QuestionObject(
            "1. Qual a festa que começa em um",
            "ano e termina em outro?",
            "img-question-01.png",
            "Páscoa",
            "Natal",
            "Ano Novo",
            "Dia do Índio",
            2,
        ),
        QuestionObject(
            "2. Qual a festa famosa pelo papai noel,",
            "renas, neve e presentes?",
            "img-question-02.png",
            "Páscoa",
            "Natal",
            "Ano Novo",
            "Dia do Índio",
            1,
        ),
        QuestionObject(
            "3. Qual povo é conhecido por suas",
            "pirâmides e faraós",
            "img-question-03.png",
            "Egipcios",
            "Maias",
            "Celtas",
            "Vikings",
            0,
        ),
        QuestionObject(
            "4. Os explorados escandinavos da",
            "Europa no século XII são os",
            "img-question-04.png",
            "Egipcios",
            "Maias",
            "Celtas",
            "Vikings",
            3,
        ),
        QuestionObject(
            "5. A empresa Apple Inc. foi",
            "fundada em meados dos anos",
            "img-question-05.png",
            "Sessenta",
            "Setenta",
            "Oitenta",
            "Noventa",
            1,
        ),
        QuestionObject(
            "6. Festa cultural realizada",
            "antes da quaresma",
            "img-question-06.png",
            "Dia da árvore",
            "Dia das mães",
            "Carnaval",
            "Páscoa",
            2,
        ),
        QuestionObject(
            "7. Indústria fonográfica",
            "se refere a indústria da",
            "img-question-07.png",
            "Música",
            "Jogos",
            "Livros",
            "Filmes",
            0,
        ),
        QuestionObject(
            "8. Valentine's day é o",
            "mesmo que dia dos",
            "img-question-08.png",
            "Pais",
            "Namorados",
            "Amigos",
            "Avós",
            1,
        ),
        QuestionObject(
            "9. Objeto que auxilia enxergar",
            "as imagens 3d no cinema",
            "img-question-09.png",
            "Pipoca",
            "Refrigerante",
            "Chocolate",
            "Óculos",
            3,
        ),
        QuestionObject(
            "10. Anualmente comemorado no",
            "dia do ano que nascemos",
            "img-question-10.png",
            "Aniversário",
            "Ano Novo",
            "Natal",
            "Tiradentes",
            0,
        ),
    )

    fun getAmountOfQuestions(): Int {
        return questions.size
    }

    fun getCurrentQuestion(index: Int): QuestionObject {
        return questions[index]
    }

    fun initOptionsObjects(
        screen: MainActivity.Screen,
        questionIndex: Int,
    ): Array<QuestionOption> {
        val firstOption = QuestionOption(
            screen.width / 4f,
            screen.height - 640f,
            questions[questionIndex].firstOption,
            questions[questionIndex].correctOptionIndex == 0,
        )
        val secondOption = QuestionOption(
            screen.width - screen.width / 4f,
            screen.height - 640f,
            questions[questionIndex].secondOption,
            questions[questionIndex].correctOptionIndex == 1,
        )
        val thirdOption = QuestionOption(
            screen.width / 4f,
            screen.height - 256f,
            questions[questionIndex].thirdOption,
            questions[questionIndex].correctOptionIndex == 2,
        )
        val fourthOption = QuestionOption(
            screen.width - screen.width / 4f,
            screen.height - 256f,
            questions[questionIndex].fourthOption,
            questions[questionIndex].correctOptionIndex == 3,
        )

        return arrayOf(
            firstOption,
            secondOption,
            thirdOption,
            fourthOption,
        )
    }
}