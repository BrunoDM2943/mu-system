package services.validator;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;

/**
 * Esta classe realiza o bloqueio de caracteres invalidos no campo JTextField
 * implementando os metodos da classe PlainDocument()
 *
 * Ao se passar um Array de Strings o mesmo realiza uma comparacao e caso haja
 * caracteres invalidos na String a ser inserida ele realiza o bloqueio.
 *
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since Release 1.0.0
 */
public class TextValidator extends PlainDocument {
	
	private static final long serialVersionUID = 9017683818568053959L;
	
	private CharSequence chars;

	/**
	 * <strong>Construtor</strong> Recebe um CharSequence que serão os
	 * caracteres a serem bloqueados.
	 *
	 * @param lockedChars
	 * @see CharSequence
	 */
	public TextValidator(CharSequence lockedChars) {
		chars = lockedChars;
	}

	/**
	 * <strong>Descricao</strong> Método sobrescrevido da classe
	 * PlainDocument(), nesta versão do metodo o mesmo realiza a checagem dos
	 * dados a serem inseridos no JComponent em questao e caso esteja de acordo
	 * (caso o caracter nao esteja bloqueado) ele permite que o mesmo seja
	 * inserido
	 *
	 * @param offset
	 *            indica o deslocamento (a posição) inicial onde o objeto String
	 *            str deverá ser inserido
	 * @param str
	 *            a String a ser inserida no JComponent
	 * @param attr
	 *            os atributos para o que será inserido
	 * @throws BadLocationException
	 *             um erro caso a insercao seja feita no local errado.
	 */
	@Override
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {

		boolean teste = false;

		if (str == null)
			super.insertString(offset, str, attr);

		int length = chars.length();

		for (int i = 0; i < length; i++) {
			if (str.contains("" + chars.charAt(i) + "")) {
				return;
			} else {
				teste = true;
			}
		}

		if (teste == true)
			super.insertString(offset, str, attr);
	}
}
