# AEDS-II
# DICAS

## 1 DICA - USE WSL


### VANTAGENS:

#### • Integração de Ferramentas:  

- WSL permite que você execute ferramentas e scripts do Linux no Windows, o que é especialmente útil para desenvolvedores que trabalham com software que é tradicionalmente executado em ambientes Linux.

#### • Desenvolvimento Cruzado:

- Facilita o desenvolvimento de aplicações que precisam ser executadas em diferentes sistemas operacionais, como ao desenvolver software que será implantado em servidores Linux.

#### • Eficiência de Recursos:

- Ao contrário das máquinas virtuais tradicionais, que emulam um sistema completo, o WSL utiliza menos recursos porque não há uma camada completa de virtualização. Isso resulta em menor uso de memória e CPU.

#### • Facilidade de Uso:

- Permite executar aplicações de linha de comando e ferramentas de desenvolvimento Linux diretamente no Windows, com integração fácil com o sistema de arquivos do Windows.

## Como instalar WSL (Windows Subsystem for Linux (Subsistema do Windows para Linux):

### PASSO A PASSO:

#### 1 passo:

- Instalar Windows Terminal na Microsoft Store.

- O Windows Terminal é uma ferramenta poderosa para trabalhar com múltiplos terminais, incluindo PowerShell, CMD e WSL.

- Abra a Microsoft Store, busque por "Windows Terminal" e clique em Instalar.

#### 2 passo:

- Abra o terminal como Administrador.
 
- Clique com o botão direito no ícone do Windows Terminal e selecione "Executar como administrador". Isso é necessário para ter permissões elevadas no sistema.

#### 3 passo:

- Execute o seguinte comando no PowerShell para habilitar o recurso do WSL:

	  wsl --install

- Este comando habilita o recurso WSL no seu sistema e também instala a distribuição padrão do Linux, geralmente o Ubuntu.

 - Nota: O sistema reiniciará automaticamente após a instalação para concluir o processo.
 - OBS: Caso não reinicie automaticamente, reinicei manualmente e continue para o proximo passo.

#### 4 passo:

- Após a reinicialização, abra o terminal novamente e configure sua distribuição do Linux:

- Se você instalou o Ubuntu, ele será configurado na primeira vez que você o iniciar.

- O terminal solicitará que você crie um nome de usuário e senha para o sistema Linux.

#### IMPORTANTE:

##### - Se algum desses passos não funcionaram na sua maquina, mande a mensagem de erro no chat gpt (ele sabe muito).

## 2 DICA/AJUDA - Aqui estão os principais comandos do Git que você vai utilizar frequentemente no terminal do Linux:

### 1. Configuração Inicial
#### - Antes de começar a usar o Git, você precisa configurá-lo com seu nome de usuário e e-mail.

	git config --global user.name "Seu Nome"
	git config --global user.email "seuemail@example.com"

#### - (Recomendo) Criar uma pasta projetos para guardar seus repositorios
    mkdir ~/projetos

#### - Navegar para a pasta projetos
    cd ~/projetos


### 2. Clonar um Repositório - git clone
#### - Esse comando é usado para copiar um repositório remoto (geralmente no GitHub ou GitLab) para a sua máquina local.

    git clone <URL-do-repositório>


### 3. Verificar o Status - git status
#### - Para verificar o status do seu repositório, ver quais arquivos foram modificados, adicionados ou estão prontos para commit.

    git status


### 4. Adicionar Arquivos ao Stage - git add
#### - Você precisa adicionar os arquivos que deseja comitar ao "stage", ou seja, prepará-los para o commit.

#### - Para adicionar um arquivo específico:

    git add <nome-do-arquivo>

#### - Para adicionar todos os arquivos modificados:

    git add .


### 5. Fazer um Commit - git commit
#### - Após adicionar os arquivos ao stage, você pode fazer um commit, que é como tirar um "snapshot" do estado atual do seu projeto.

#### - Comentar diretamente no terminal:

    git commit -m "Sua mensagem de commit"

#### - Para editar a mensagem no editor padrão:

    git commit


### 6. Enviar Alterações para o Repositório Remoto - git push
#### - Após fazer commits, você envia as alterações para o repositório remoto com o comando git push.

    git push origin <nome-da-branch>

#### - Se estiver na branch principal, use:

    git push origin main

#### - Ou, se seu repositorio tiver apenas uma branch:

    git push

#### - Autenticar (se necessário)
- username = seu username do github
- senha = token

 * Como gerar um Token de Acesso Pessoal

    * Acesse as Configurações do GitHub:

       - Vá para GitHub e faça login na sua conta.
       - Clique na sua foto de perfil no canto superior direito e selecione "Settings".

    * Gere um Novo Token:

       - No menu à esquerda, clique em "Developer settings".
       - Clique em "Personal access tokens".
       - Clique em "Tokens(classic)".
       - Clique no botão "Generate new token".
       - De um nome, e marque a permição REPO.
       - Salve o token, ele so é visivel no momento em que é criado.

#### - Para evitar essa autenticacao toda hora, baixe a extencao WSL no vscode e faça seus commits pelo terminal do vscode, ele apenas pedirá a integração uma vez.

### 7. Atualizar o Repositório Local - git pull
#### - O comando git pull busca as mudanças do repositório remoto e as mescla com o código local.

    git pull origin <nome-da-branch>

#### - Se estiver na branch principal:

    git pull origin main


### 8. Criar uma Nova Branch - git branch e git checkout
#### - Branches são ramificações de código que você pode trabalhar sem afetar o código principal.

#### - Para criar uma nova branch:

    git branch <nome-da-branch>

#### - Para trocar para essa branch:

    git checkout <nome-da-branch>

#### - Para criar e já trocar para a nova branch:

    git checkout -b <nome-da-branch>


### 9. Ver as Branches Disponíveis - git branch
#### - Esse comando lista todas as branches disponíveis no repositório.

    git branch


### 10. Mesclar uma Branch com outra - git merge
#### - Depois de trabalhar em uma branch separada, você pode mesclar as alterações de volta para a branch principal.

#### - Primeiro, vá para a branch que você quer mesclar (geralmente main):

    git checkout main

#### - Agora mescle a branch desejada:

    git merge <nome-da-branch>


### 11. Reverter um Commit - git revert
#### - Se você cometer um erro, pode desfazer um commit específico com o git revert.

    git revert <ID-do-commit>


### 12. Ver Histórico de Commits - git log
#### - Para visualizar o histórico de commits.

    git log


### 13. Remover Arquivos do Stage - git reset
#### - Se você adicionou algo ao stage por engano, pode removê-lo antes de comitar:

#### - Para remover um arquivo do stage:

    git reset <nome-do-arquivo>

#### - Para remover todos os arquivos do stage:

    git reset


### 14. Remover o Último Commit Local - git reset --soft
#### - Se você cometeu um erro no último commit, pode removê-lo (mantendo as mudanças locais) com:

    git reset --soft HEAD^


Com esses comandos, você será capaz de realizar as operações mais importantes no Git.
