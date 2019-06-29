package dev.jocatelo.winninglotto

object WinningLottoLibrary{
    var winningLottoMap = hashMapOf<Int, WinningLotto>()

    fun setLotto( time:Int,  lotto: WinningLotto)
    {
        winningLottoMap[time] = lotto
    }

    fun getLotto(time: Int): WinningLotto? {
        return winningLottoMap[time]
    }
}
