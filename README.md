# 📍 Sistema de Eventos de Porto Alegre (Java Console)

Projeto desenvolvido para a Unidade Curricular de **Programação de Soluções Computacionais**. Trata-se de uma aplicação desenvolvida na linguagem Java sob o paradigma Orientado a Objetos (POO), executada via Console/Terminal, voltada para o cadastro, organização, consulta e notificação de eventos na cidade de Porto Alegre.

---

## 📌 Sobre o Uso do Git e GitHub no Projeto

O **Git** foi utilizado como sistema de controle de versão distribuído para monitorar as alterações no código-fonte, garantindo a organização e a integridade da aplicação ao longo do desenvolvimento.

O **GitHub** atua como a plataforma principal de hospedagem do repositório, permitindo o compartilhamento do código-fonte, a documentação pública do escopo e a disponibilização dos entregáveis acadêmicos.

---

## 🎥 Vídeos

* **Demonstração do Sistema em Execução:** [Inserir o link do vídeo de demonstração do sistema via YouTube/Drive]
* **Apresentação do Projeto:** [Inserir o link da apresentação do projeto]

---

## 📚 Glossário

Conceitos centrais de Java, POO e Controle de Versão aplicados no projeto:

* **Classe Abstrata (`EntidadeBase`):** Modelo de classe que não pode ser instanciado diretamente, servindo como superclasse para forçar a implementação dos métodos obrigatórios `getIdentificador()` e `exibirResumo()`.
* **Métodos Virtuais / Abstratos:** Métodos declarados na classe abstrata e implementados de forma polimórfica nas subclasses (`Usuario` e `Evento`).
* **Encapsulamento:** Prática de manter os atributos protegidos (`private`) com acesso controlado através de métodos *getters* e *setters*.
* **Enum (`CategoriaEvento`):** Estrutura usada para limitar e padronizar o conjunto fixo de categorias de eventos no sistema (ex: `FESTIVAL`, `SHOW`, `TEATRO`, `ESPORTES`, `GASTRONOMIA`, `ACADEMICO`, `OUTROS`).
* **`LocalDateTime`:** API nativa de data e hora do Java utilizada para controlar os horários dos eventos, permitindo a ordenação cronológica e a identificação de status (*Ocorrendo agora*, *Futuro* e *Já ocorreu*).
* **Persistência em Arquivo (`events.data`):** Leitura e escrita textual utilizando a biblioteca `java.io`, garantindo a gravação dos dados em disco entre as execuções do programa.
* **Git:** Sistema de controle de versão distribuído utilizado para acompanhar o histórico de modificações do projeto.
* **GitHub:** Plataforma em nuvem baseada em Git utilizada para hospedar o repositório do projeto.

---

## 🔗 Arquivos e Links

* 🐙 **Repositório no GitHub:** https://github.com/
* 📄 **Arquivo de Dados:** `events.data` (armazenado na raiz do projeto)
* 💻 **Código-Fonte (.java):** Arquivos contidos na estrutura principal do projeto (`Main.java`, `Evento.java`, `Usuario.java`, `GerenciadorEventos.java`, `EntidadeBase.java`, `CategoriaEvento.java`).

---

## 🚀 Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU-USUARIO/sistema-eventos-poa.git](https://github.com/SEU-USUARIO/sistema-eventos-poa.git)
