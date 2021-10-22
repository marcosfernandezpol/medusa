import React from 'react'
import { connect } from 'react-redux';
import { Field, FieldArray, reduxForm } from 'redux-form'
import validate from '../../common/components/validate'
import { FormattedMessage } from 'react-intl';

const renderField = ({ input, label, type, meta: { touched, error } }) => (
  <div>
    <label>{label}</label>
    <div>
      <input {...input} type={type} placeholder={label} />
      {touched && error && <span>{error}</span>}
    </div>
  </div>
);

const renderYears = ({ fields }) => (
  <div className="text-center">
    <div class="mx-auto ">
      <button type="button" class="btn btn-primary mt-2 mb-4" onClick={() => fields.push({})}>
        <FormattedMessage id="project.global.button.addElement" />
      </button>
    </div>
    {fields.map((year, index) => (
      <div key={index}>
        <h4>Input {index + 1}</h4>
        <Field
          name={`${year}.year`}
          type="number"
          step="1"
          component={renderField}
          label="Year"
        />
        <Field
          name={`${year}.benefits`}
          type="number"
          component={renderField}
          label="Benefits"
        />
		<button type="button" title="Remove Year" class="btn btn-secondary mx-5 my-4" onClick={() => fields.remove(index)}>
			<FormattedMessage id="project.global.button.deleteElement" />
        </button>
      </div>
    ))}
  </div>
);

let FieldArraysForm = (props) => {
  const { handleSubmit, pristine, reset, submitting } = props;
  return (
    <form onSubmit={handleSubmit}>
      <FieldArray name="benefitsList" component={renderYears} />
      <div className="d-flex justify-content-around">
        <button class="btn btn-secondary" type="submit" disabled={submitting}>
          <FormattedMessage id="project.global.button.submit" />
        </button>
        <button class="btn btn-secondary mx-5" type="button" disabled={pristine || submitting} onClick={reset}>
          <FormattedMessage id="project.global.button.clearValues" />
        </button>
      </div>
    </form>
  );
};



export default reduxForm({
  form: 'fieldArrays', // a unique identifier for this form
  validate,
})(FieldArraysForm);





