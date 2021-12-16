package org.oppia.android.domain.classify.rules.algebraicexpressioninput

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import org.oppia.android.domain.classify.RuleClassifier
import org.oppia.android.domain.classify.rules.AlgebraicExpressionInputRules
import org.oppia.android.domain.classify.rules.mathequationinput.MathEquationInputIsEquivalentToRuleClassifierProvider
import org.oppia.android.domain.classify.rules.mathequationinput.MathEquationInputMatchesExactlyWithRuleClassifierProvider
import org.oppia.android.domain.classify.rules.mathequationinput.MathEquationInputMatchesUpToTrivialManipulationsRuleClassifierProvider

/**
 * Module that binds rule classifiers corresponding to the algebraic expression input interaction.
 */
@Module
class AlgebraicExpressionInputModule {
  @Provides
  @IntoMap
  @StringKey("MatchesExactlyWith")
  @AlgebraicExpressionInputRules
  internal fun provideAlgebraicExpressionInputMatchesExactlyWithRuleClassifier(
    classifierProvider: AlgebraicExpressionInputMatchesExactlyWithRuleClassifierProvider
  ): RuleClassifier = classifierProvider.createRuleClassifier()

  @Provides
  @IntoMap
  @StringKey("MatchesUpToTrivialManipulations")
  @AlgebraicExpressionInputRules
  internal fun provideAlgebraicExpressionInputMatchesUpToTrivialManipulationsRuleClassifier(
    classifierProvider:
      MathEquationInputMatchesUpToTrivialManipulationsRuleClassifierProvider
  ): RuleClassifier = classifierProvider.createRuleClassifier()

  @Provides
  @IntoMap
  @StringKey("IsEquivalentTo")
  @AlgebraicExpressionInputRules
  internal fun provideAlgebraicExpressionInputIsEquivalentToRuleClassifier(
    classifierProvider: MathEquationInputIsEquivalentToRuleClassifierProvider
  ): RuleClassifier = classifierProvider.createRuleClassifier()
}
