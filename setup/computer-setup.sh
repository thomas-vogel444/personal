# Set up Prezto (assuming zsh is the default)
git clone --recursive https://github.com/sorin-ionescu/prezto.git "${ZDOTDIR:-$HOME}/.zprezto"

setopt EXTENDED_GLOB
for rcfile in "${ZDOTDIR:-$HOME}"/.zprezto/runcoms/^README.md(.N); do
  ln -s "$rcfile" "${ZDOTDIR:-$HOME}/.${rcfile:t}"
done

# Install Homebrew
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"

brew cask install spectacle
brew cask install intellij-idea-ce

brew tap adoptopenjdk/openjdk
brew cask install adoptopenjdk8
brew cask install 1password
brew cask install brave-browser
brew cask install docker
brew install minikube
brew cask install evernote
brew cask install slack
brew cask install alfred
brew cask install iterm2
brew cask install authy